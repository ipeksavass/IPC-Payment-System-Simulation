package com.ipeksavas.adminservice.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.ipeksavas.adminservice.data.entity.Receipt;
import com.ipeksavas.adminservice.data.entity.ReceiptItem;
import com.ipeksavas.adminservice.data.entity.ReceiptWithItems;

import java.util.List;

@Dao
public interface ReceiptDao {
    @Insert
    long insertReceipt(Receipt receipt);
    
    @Insert
    void insertReceiptItems(List<ReceiptItem> items);
    
    @Query("DELETE FROM receipt_table WHERE receiptId=:id")
    void deleteReceiptById(int id);
    
    @Transaction
    @Query("SELECT * FROM receipt_table WHERE receiptDateTime LIKE (substr(:date, 7, 4) || '-' || substr(:date, 4, 2) || '-' || substr(:date, 1, 2) || '%')")
    List<ReceiptWithItems> getTransactionsByDate(String date);
    
    @Query("UPDATE receipt_table SET status = :newStatus WHERE receiptId = :receiptId")
    void updateReceiptStatus(int receiptId, String newStatus);
}
