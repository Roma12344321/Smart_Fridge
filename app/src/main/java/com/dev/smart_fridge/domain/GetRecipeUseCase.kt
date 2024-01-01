package com.dev.smart_fridge.domain

import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun getRecipe() : List<RecipeItem> {
        return productRepository.getRecipe()
    }
}