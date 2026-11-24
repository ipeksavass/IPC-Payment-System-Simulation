package com.ipeksavas.restfulapplication.domain.model

data class CartItem(
    val product: Product,
    val quantity: Int
){
    val totalPrice: Double get() = product.price * quantity
}
