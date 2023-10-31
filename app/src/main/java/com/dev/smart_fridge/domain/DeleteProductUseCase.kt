package com.dev.smart_fridge.domain

class DeleteProductUseCase(private val productRepository: ProductRepository) {
    fun deleteProduct(productId: Long) {
        productRepository.deleteProduct(productId)
    }
}