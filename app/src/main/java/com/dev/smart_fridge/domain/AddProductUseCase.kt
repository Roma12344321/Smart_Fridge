package com.dev.smart_fridge.domain

import javax.inject.Inject


class AddProductUseCase @Inject constructor(private val productRepository: ProductRepository) {
    fun addProduct(product: Product) {
        productRepository.addProduct(product)
    }
}