package com.ipeksavas.adminservice.data.repository;

import android.content.Context;

import com.ipeksavas.adminservice.data.AppDatabase;
import com.ipeksavas.adminservice.data.dao.ReceiptDao;
import com.ipeksavas.adminservice.data.entity.Receipt;
import com.ipeksavas.adminservice.data.entity.ReceiptItem;
import com.ipeksavas.adminservice.domain.repository.ReceiptRepository;

import java.util.List;

public class ReceiptRepositoryImpl implements ReceiptRepository {
    private final ReceiptDao receiptDao;
    
    public ReceiptRepositoryImpl(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        this.receiptDao = db.receiptDao();
    }
    
    @Override
    public long saveReceipt(Receipt receipt, List<ReceiptItem> items) {
        long receiptId = receiptDao.insertReceipt(receipt);
        
        if(items != null && !items.isEmpty()){
            for(ReceiptItem i : items){
                i.setParentReceiptId((int) receiptId);
            }
            receiptDao.insertReceiptItems(items);
        }
        return receiptId;
    }
    
    @Override
    public void deleteReceiptById(int id) {
        receiptDao.deleteReceiptById(id);
    }
}
