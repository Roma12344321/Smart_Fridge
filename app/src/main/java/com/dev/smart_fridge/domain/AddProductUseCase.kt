package com.dev.smart_fridge.domain



class AddProductUseCase(private val productRepository: ProductRepository) {
    fun addProduct(product:Product){
        productRepository.addProduct(product)
    }
}