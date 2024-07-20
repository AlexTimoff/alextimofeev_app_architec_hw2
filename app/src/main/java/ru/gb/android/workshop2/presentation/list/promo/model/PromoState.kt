package ru.gb.android.workshop2.presentation.list.promo.model

import android.content.Context

typealias ErrorProvider = (Context) -> String

data class PromosListState (
    val isLoading: Boolean = false,
    val promosList: List<PromoState> = mutableListOf(),
    val hasError: Boolean = false,
    val errorProvider: ErrorProvider = {""}
)

data class PromoState (
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val image: String = "",
)