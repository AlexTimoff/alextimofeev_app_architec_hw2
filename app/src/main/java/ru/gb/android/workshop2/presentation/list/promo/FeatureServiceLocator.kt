package ru.gb.android.workshop2.presentation.list.promo

import ru.gb.android.workshop2.ServiceLocator

object FeatureServiceLocator {

    fun providePromoViewModelFactory(): PromoViewModelFactory{
        return PromoViewModelFactory(
            promoStateFactory = providePromoStateFactory(),
            consumePromosUseCase =  ServiceLocator.provideConsumePromosUseCase()
        )
    }

    private fun providePromoStateFactory(): PromoStateFactory {
        return PromoStateFactory()
    }
}