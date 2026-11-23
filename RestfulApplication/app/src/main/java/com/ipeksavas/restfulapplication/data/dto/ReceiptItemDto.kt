package com.ipeksavas.restfulapplication.data.dto

data class ReceiptItemDto(
    val itemId: Int = 0,
    val parentReceiptId: Int = 0,
    
    val productId: Int = 0,
    val productName:String = "",
    val productPrice: Double = 0.0,
    val productQuantity: Int = 0,
    val productDepartmentId: Int = 0,
    val productDepartmentName: String = ""
)
