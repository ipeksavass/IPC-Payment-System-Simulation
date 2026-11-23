package com.ipeksavas.restfulapplication.utils

object IpcProtocol {
    
    const val ADMIN_SERVICE_PACKAGE = "com.ipeksavas.adminservice"
    const val ADMIN_SERVICE_CLASS = "com.ipeksavas.adminservice.service.AdminService"
    
    //Ipc commands
    const val MSG_SEND_PAYMENT = 1
    const val MSG_PAYMENT_RESPONSE = 2
    const val MSG_GET_TRANSACTIONS = 3
    const val MSG_REFUND_REQUEST = 4
    
    // Bundle data keys
    const val KEY_PAYMENT_DATA = "payment_data"
    const val KEY_RESPONSE_DATA = "response_data"
    const val KEY_PAYMENT_RESPONSE = "payment_response"
    const val KEY_QUERY_DATE = "query_date"
}