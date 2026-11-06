package com.ipeksavas.adminservice.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ipeksavas.adminservice.data.entity.Receipt;
import com.ipeksavas.adminservice.data.entity.ReceiptItem;

import java.util.List;

@Dao
public interface ReceiptDao {
    @Insert
    long insertReceipt(Receipt receipt);
    
    @Insert
    void insertReceiptItems(List<ReceiptItem> items);
    
    @Query("DELETE FROM receipt_table WHERE receiptId=:id")
    void deleteReceiptById(int id);
    
}
