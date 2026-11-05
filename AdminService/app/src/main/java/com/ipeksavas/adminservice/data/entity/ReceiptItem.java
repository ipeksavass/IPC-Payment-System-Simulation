package com.ipeksavas.adminservice.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "receipt_item_table",
        foreignKeys = @ForeignKey(
                entity = Receipt.class,
                parentColumns = "receiptId",
                childColumns = "parentReceiptId",
                onDelete = ForeignKey.CASCADE
        )
)
public class ReceiptItem {
    @PrimaryKey(autoGenerate = true)
    private int itemId;
    private int parentReceiptId;
    
    private int productId;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private int productDepartmentId;
    private String productDepartmentName;
    
    public int getItemId() {
        return itemId;
    }
    
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    
    public int getParentReceiptId() {
        return parentReceiptId;
    }
    
    public void setParentReceiptId(int parentReceiptId) {
        this.parentReceiptId = parentReceiptId;
    }
    
    public int getProductDepartmentId() {
        return productDepartmentId;
    }
    
    public void setProductDepartmentId(int productDepartmentId) {
        this.productDepartmentId = productDepartmentId;
    }
    
    public String getProductDepartmentName() {
        return productDepartmentName;
    }
    
    public void setProductDepartmentName(String productDepartmentName) {
        this.productDepartmentName = productDepartmentName;
    }
    
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public double getProductPrice() {
        return productPrice;
    }
    
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    
    public int getProductQuantity() {
        return productQuantity;
    }
    
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
