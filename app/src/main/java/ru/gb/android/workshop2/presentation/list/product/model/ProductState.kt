package ru.gb.android.workshop2.presentation.list.product.model

import android.content.Context

typealias ErrorProvider = (Context) -> String


data class ProductList(
    val isLoading: Boolean = false,
    val productList: List<ProductState> = mutableListOf(),
    val hasError: Boolean = false,
    val errorProvider: ErrorProvider = {""}
)


data class ProductState (
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val price: String = "",
    val hasDiscount: Boolean = false,
    val discount: String = "",
)