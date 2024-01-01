package com.dev.smart_fridge.domain

import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun getRecipe(promt : String) : String {
        return productRepository.getRecipe(promt)
    }
}