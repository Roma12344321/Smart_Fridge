package com.dev.smart_fridge.domain

class GetProductItemUseCase(private val productRepository: ProductRepository) {
    fun getProductIem(productId: Long): Product {
        return productRepository.getProductItem(productId)
    }
}