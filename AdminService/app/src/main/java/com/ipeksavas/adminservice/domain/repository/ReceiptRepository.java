package com.ipeksavas.adminservice.domain.repository;

import com.ipeksavas.adminservice.data.entity.Receipt;
import com.ipeksavas.adminservice.data.entity.ReceiptItem;
import com.ipeksavas.adminservice.data.entity.ReceiptWithItems;

import java.util.List;

public interface ReceiptRepository {
    long saveReceipt(Receipt receipt, List<ReceiptItem> items);
    void deleteReceiptById(int id);
    List<ReceiptWithItems> getTransactionsByDate(String date);
    void updateReceiptStatus(int receiptId, String newStatus);
}
