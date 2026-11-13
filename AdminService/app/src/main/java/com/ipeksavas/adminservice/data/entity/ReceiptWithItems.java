package com.ipeksavas.adminservice.data.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ReceiptWithItems {
    @Embedded
    public Receipt receipt;
    @Relation(
            parentColumn = "receiptId",
            entityColumn = "parentReceiptId"
    )
    public List<ReceiptItem> items;
}
