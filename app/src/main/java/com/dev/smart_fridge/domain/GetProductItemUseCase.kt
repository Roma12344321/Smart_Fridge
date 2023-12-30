package com.dev.smart_fridge.domain

import javax.inject.Inject

class GetProductItemUseCase @Inject constructor(private val productRepository: ProductRepository) {
    fun getProductIem(productId: Long): Product{
        return productRepository.getProductItem(productId)
    }
}