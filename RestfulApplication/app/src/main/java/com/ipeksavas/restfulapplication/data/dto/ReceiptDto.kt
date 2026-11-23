package com.ipeksavas.restfulapplication.data.dto

data class ReceiptDto(
    val receiptId: Int = 0,
    val receiptDateTime: String = "",
    val totalAmount: Double = 0.0,
    val paymentType: String = "",
    val status: String = "",
    val items: List<ReceiptItemDto> = emptyList()
)