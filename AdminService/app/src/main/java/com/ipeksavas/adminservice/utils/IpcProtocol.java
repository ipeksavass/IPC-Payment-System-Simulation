package com.ipeksavas.adminservice.utils;
public final class IpcProtocol {
    
    // --- IPC Command Codes (Message.what) ---
    public static final int MSG_SEND_PAYMENT = 1;
    public static final int MSG_PAYMENT_RESPONSE = 2;
    public static final int MSG_GET_TRANSACTIONS = 3;
    public static final int MSG_REFUND_REQUEST = 4;
    public static final int MSG_UPDATE_PAYMENT_STATUS = 5;
    
    // --- Bundle Data Keys (Messenger) ---
    public static final String KEY_PAYMENT_DATA = "payment_data";//String / JSON)
    public static final String KEY_RESPONSE_DATA = "response_data";//String / JSON) data getString
    public static final String KEY_PAYMENT_RESPONSE = "payment_response";//id or -1 getLong
    public static final String KEY_QUERY_DATE = "query_date";
    public static final String KEY_RECEIPT_ID = "receiptId";
    public static final String KEY_IS_SUCCESS = "isSuccess";
    public static final String KEY_TOTAL_AMOUNT = "totalAmount";
    
    // --- Intent and PaymentApp (C )  ---
    public static final String PAYMENT_APP_PACKAGE = "com.ipeksavas.paymentApplication";
    public static final String ACTION_START_PAYMENT = "com.ipeksavas.paymentApplication.START_PAYMENT";
    
    private IpcProtocol(){}
}
