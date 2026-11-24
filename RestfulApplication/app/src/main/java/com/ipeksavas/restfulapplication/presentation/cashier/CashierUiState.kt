package com.ipeksavas.restfulapplication.presentation.cashier

import com.ipeksavas.restfulapplication.domain.model.CartItem
import com.ipeksavas.restfulapplication.domain.model.Product

data class CashierUiState(
    val products: List<Product> = emptyList(),
    val cartItems: List<CartItem> = emptyList(),
    val isServiceConnected: Boolean = false,
    val isLoading: Boolean = false,
    val userMessage: String? = null
){
    val totalAmount: Double
        get() = cartItems.sumOf { it.totalPrice}
    val productsByDepartment: Map<String, List<Product>>
        get() = products.groupBy { it.departmentName }
}