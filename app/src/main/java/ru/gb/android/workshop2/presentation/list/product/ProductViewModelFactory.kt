package ru.gb.android.workshop2.presentation.list.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.gb.android.workshop2.domain.product.ConsumeProductsUseCase
import ru.gb.android.workshop2.domain.promo.ConsumePromosUseCase

class ProductViewModelFactory (
    private val consumeProductsUseCase: ConsumeProductsUseCase,
    private val productStateFactory: ProductStateFactory,
    private val consumePromosUseCase: ConsumePromosUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(ProductViewModel::class.java) ->{
                @Suppress("UNCHEKED_CAST")
                return ProductViewModel(
                    consumeProductsUseCase = consumeProductsUseCase,
                    productFactory= productStateFactory,
                    consumePromosUseCase = consumePromosUseCase
                ) as  T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}