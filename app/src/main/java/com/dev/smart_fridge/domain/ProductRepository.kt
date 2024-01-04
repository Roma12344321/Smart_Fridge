package com.dev.smart_fridge.domain

import androidx.lifecycle.LiveData

interface ProductRepository {
    fun addProduct(product: Product)
    fun deleteProduct(productId: Long)
    fun getAllProduct(): LiveData<List<Product>>
    fun getProductItem(productId: Long): Product
    suspend fun getRecipe() : List<RecipeItem>
    suspend fun getRecipeDetailInformation(name : String) : RecipeDetailInformation
}