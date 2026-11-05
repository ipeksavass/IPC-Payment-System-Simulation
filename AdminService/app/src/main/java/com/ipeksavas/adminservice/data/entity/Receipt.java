package com.ipeksavas.adminservice.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName= "receipt_table")
public class Receipt {
    @PrimaryKey(autoGenerate = true)
    private int receiptId;
    private String receiptDateTime;
    private double totalAmount;
    private String paymentType;
    private String status;
    
    public String getPaymentType() {
        return paymentType;
    }
    
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    
    public String getReceiptDateTime() {
        return receiptDateTime;
    }
    
    public void setReceiptDateTime(String receiptDateTime) {
        this.receiptDateTime = receiptDateTime;
    }
    
    public int getReceiptId() {
        return receiptId;
    }
    
    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
