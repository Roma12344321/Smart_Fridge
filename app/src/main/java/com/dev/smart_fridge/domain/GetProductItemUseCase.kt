package com.dev.smart_fridge.domain

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Single

class GetProductItemUseCase(private val productRepository: ProductRepository) {
    fun getProductIem(productId: Long): Product{
        return productRepository.getProductItem(productId)
    }
}