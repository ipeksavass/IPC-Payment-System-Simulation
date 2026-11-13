package com.ipeksavas.adminservice.data.dto;

import com.ipeksavas.adminservice.data.entity.Receipt;
import com.ipeksavas.adminservice.data.entity.ReceiptItem;

import java.util.List;

public class IncomingPaymentDTO {
    private Receipt receipt;
    private List<ReceiptItem> items;
    public Receipt getReceipt(){
        return receipt;
    }
    public List<ReceiptItem> getItems(){
        return items;
    }
}
