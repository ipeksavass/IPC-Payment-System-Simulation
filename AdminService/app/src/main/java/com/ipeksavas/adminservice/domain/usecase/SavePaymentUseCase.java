package com.ipeksavas.adminservice.domain.usecase;

import com.ipeksavas.adminservice.data.entity.Receipt;
import com.ipeksavas.adminservice.data.entity.ReceiptItem;
import com.ipeksavas.adminservice.domain.repository.ReceiptRepository;

import java.util.List;

public class SavePaymentUseCase {
    private final ReceiptRepository repository;
    
    public SavePaymentUseCase(ReceiptRepository receiptRepository){
        this.repository = receiptRepository;
    }
    
    public long execute(Receipt receipt, List<ReceiptItem> items){
        long id = repository.saveReceipt(receipt, items);
        if(id % 10 == 0) return -1;
        return id;
    }
}
