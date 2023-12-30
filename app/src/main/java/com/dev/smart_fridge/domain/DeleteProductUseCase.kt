package com.dev.smart_fridge.domain

import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(private val productRepository: ProductRepository) {
    fun deleteProduct(productId: Long) {
        productRepository.deleteProduct(productId)
    }
}