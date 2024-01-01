package com.dev.smart_fridge.domain

import javax.inject.Inject

class GetRecipeDetailInformationUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend fun getRecipeDetailInformation(name : String) : String {
        return repository.getRecipeDetailInformation(name)
    }
}