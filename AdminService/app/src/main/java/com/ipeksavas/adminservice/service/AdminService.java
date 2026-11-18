package com.ipeksavas.adminservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.ipeksavas.adminservice.data.dto.IncomingPaymentDTO;
import com.ipeksavas.adminservice.data.entity.ReceiptWithItems;
import com.ipeksavas.adminservice.data.repository.ReceiptRepositoryImpl;
import com.ipeksavas.adminservice.domain.repository.ReceiptRepository;
import com.ipeksavas.adminservice.domain.usecase.SavePaymentUseCase;
import com.ipeksavas.adminservice.utils.IpcProtocol;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AdminService extends Service {
    private static final String TAG = "AdminService";
    
    private ReceiptRepository receiptRepository;
    private SavePaymentUseCase savePaymentUC;
    private final Gson gson = new Gson();
    
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    
    private volatile Messenger globalReplyTo = null;
    private Messenger messenger;
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        receiptRepository = new ReceiptRepositoryImpl(this);
        savePaymentUC = new SavePaymentUseCase(receiptRepository);
        
        messenger = new Messenger(new IncomingHandler(this));
        Log.i(TAG,"AdminService has started and is waiting for a connection.");
    }
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
    
    private static class IncomingHandler extends Handler {
        private final WeakReference<AdminService> serviceRef;
        IncomingHandler(AdminService service){
            super(Looper.getMainLooper());
            this.serviceRef = new WeakReference<>(service);
        }
        
        @Override
        public void handleMessage(@NonNull Message msg) {
            AdminService service = serviceRef.get();
            if(service == null) return;
            
            switch(msg.what){
                case IpcProtocol.MSG_SEND_PAYMENT:
                    service.handleNewPayment(msg);
                    break;
                case IpcProtocol.MSG_GET_TRANSACTIONS:
                    service.handleGetTransactions(msg);
                    break;

                case IpcProtocol.MSG_UPDATE_PAYMENT_STATUS:
                    service.handleUpdatePaymentStatus(msg);
                    break;
//              case IpcProtocol.MSG_REFUND_REQUEST:
//                  service.handleRefundRequest(msg);
//                  break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    
    private void handleNewPayment(Message msg){
        Bundle bundle = msg.getData();
        String json = bundle.getString(IpcProtocol.KEY_PAYMENT_DATA);
        
        globalReplyTo = msg.replyTo;
        
        executorService.execute( () -> {
            try {
                IncomingPaymentDTO dto = gson.fromJson(json, IncomingPaymentDTO.class);
                if (dto != null) {
                    long resultId = savePaymentUC.execute(dto.getReceipt(), dto.getItems());
                    if (resultId != -1) {
                        if ("CASH".equals(dto.getReceipt().getPaymentType())) {
                            receiptRepository.updateReceiptStatus((int) resultId, "SUCCESSFUL");
                            sendResponse(globalReplyTo, IpcProtocol.MSG_PAYMENT_RESPONSE, resultId, null);
                            globalReplyTo = null;
                        } else {
                            Intent intent = new Intent(IpcProtocol.ACTION_START_PAYMENT);
                            intent.setPackage(IpcProtocol.PAYMENT_APP_PACKAGE);
                            intent.putExtra(IpcProtocol.KEY_TOTAL_AMOUNT, dto.getReceipt().getTotalAmount());
                            intent.putExtra(IpcProtocol.KEY_RECEIPT_ID, (int) resultId);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }else{
                        sendResponse(globalReplyTo, IpcProtocol.MSG_PAYMENT_RESPONSE, -1, null);
                        globalReplyTo = null;                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "An error occurred while processing the payment:", e);
            }
        });
    }
    
    public void handleUpdatePaymentStatus(Message msg){
        Bundle bundle = msg.getData();
        int receiptId = bundle.getInt(IpcProtocol.KEY_RECEIPT_ID);
        boolean isSuccess = bundle.getBoolean(IpcProtocol.KEY_IS_SUCCESS);
        
        executorService.execute( () -> {
            try{
                if(isSuccess){
                    receiptRepository.updateReceiptStatus(receiptId, "SUCCESSFUL");
                    if(globalReplyTo != null){
                        sendResponse(globalReplyTo, IpcProtocol.MSG_PAYMENT_RESPONSE, receiptId, null);
                        globalReplyTo = null;
                    }
                }else {
                    receiptRepository.updateReceiptStatus(receiptId, "FAILED");
                    if (globalReplyTo != null) {
                        sendResponse(globalReplyTo, IpcProtocol.MSG_PAYMENT_RESPONSE, -1, null);
                        globalReplyTo = null;
                    }
                }
            }catch(Exception e){
                Log.e(TAG, "An error occurred while updating the payment status:", e);
            }
        });
    }
    private void handleGetTransactions(Message msg){
        String date = msg.getData().getString(IpcProtocol.KEY_QUERY_DATE);
        Messenger replyTo = msg.replyTo;
        
        executorService.execute( () -> {
            try{
                List<ReceiptWithItems> results = receiptRepository.getTransactionsByDate(date);
                String jsonResponse = gson.toJson(results);
                sendResponse(replyTo, IpcProtocol.MSG_PAYMENT_RESPONSE, 0, jsonResponse);
            }catch(Exception e){
                Log.e(TAG, "Query error" + e.getMessage());
            }
        });
    }
    private void sendResponse(Messenger replyTo, int what, long resultId, String jsonResponse){
        if(replyTo != null){
            try{
                Message response = Message.obtain(null,what);
                Bundle data = new Bundle();
                if(resultId != 0) data.putLong(IpcProtocol.KEY_PAYMENT_RESPONSE,resultId);
                if(jsonResponse != null) data.putString(IpcProtocol.KEY_RESPONSE_DATA, jsonResponse);
                response.setData(data);
                replyTo.send(response);
            }catch(RemoteException e){
                Log.e(TAG,"The connection was lost while sending the response:",e);
            }
        }
    }
    
    @Override
    public void onDestroy(){
        super.onDestroy();
        executorService.shutdown();
        Log.i(TAG,"AdminService has been destroyed.");
    }
}
