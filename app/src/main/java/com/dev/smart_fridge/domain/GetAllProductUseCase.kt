package com.dev.smart_fridge.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(private val productRepository: ProductRepository) {
    fun getAllProduct(): LiveData<List<Product>> {
        return productRepository.getAllProduct()
    }
}