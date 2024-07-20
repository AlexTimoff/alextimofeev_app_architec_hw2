package ru.gb.android.workshop2.presentation.list.product

import ru.gb.android.workshop2.ServiceLocator

object FeatureServiceLocator {

    fun provideProductViewModelFactory(): ProductViewModelFactory {
        return ProductViewModelFactory(
            consumeProductsUseCase = ServiceLocator.provideConsumeProductsUseCase(),
            productStateFactory = provideProductStateFactory(),
            consumePromosUseCase = ServiceLocator.provideConsumePromosUseCase(),
        )
    }

    private fun provideProductStateFactory(): ProductStateFactory {
        return ProductStateFactory(
            discountFormatter = ServiceLocator.provideDiscountFormatter(),
            priceFormatter = ServiceLocator.providePriceFormatter(),
        )
    }
}