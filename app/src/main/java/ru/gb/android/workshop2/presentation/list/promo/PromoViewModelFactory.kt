package ru.gb.android.workshop2.presentation.list.promo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.gb.android.workshop2.domain.promo.ConsumePromosUseCase

class PromoViewModelFactory (
    private val promoStateFactory: PromoStateFactory,
    private val consumePromosUseCase: ConsumePromosUseCase,
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(PromoViewModel::class.java) -> {
                @Suppress("UNCHEKED_CAST")
                return PromoViewModel(
                    promoStateFactory = promoStateFactory,
                    consumePromosUseCase = consumePromosUseCase
                ) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}