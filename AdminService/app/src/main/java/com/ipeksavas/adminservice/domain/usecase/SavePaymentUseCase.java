package com.ipeksavas.adminservice.domain.usecase;

import com.ipeksavas.adminservice.data.entity.Receipt;
import com.ipeksavas.adminservice.data.entity.ReceiptItem;
import com.ipeksavas.adminservice.domain.repository.ReceiptRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SavePaymentUseCase {
    private final ReceiptRepository repository;
    
    public SavePaymentUseCase(ReceiptRepository receiptRepository){
        this.repository = receiptRepository;
    }
    
    public long execute(Receipt receipt, List<ReceiptItem> items){
        if (receipt == null) return -1;
        
        receipt.setStatus("WAITING");
        
        if (receipt.getReceiptDateTime() == null || receipt.getReceiptDateTime().isEmpty()) {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date());
            receipt.setReceiptDateTime(currentDate);
        }
        
        return repository.saveReceipt(receipt, items);
    }
}
