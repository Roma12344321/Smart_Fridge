package com.dev.smart_fridge.domain

import androidx.lifecycle.LiveData

class GetAllProductUseCase(private val productRepository: ProductRepository) {
    fun getAllProduct(): LiveData<List<Product>> {
        return productRepository.getAllProduct()
    }
}