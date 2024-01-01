package com.dev.smart_fridge.data

import androidx.lifecycle.LiveData
import com.dev.smart_fridge.BuildConfig
import com.dev.smart_fridge.domain.Product
import com.dev.smart_fridge.domain.ProductRepository
import com.dev.smart_fridge.domain.RecipeItem
import com.google.ai.client.generativeai.GenerativeModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductRepository {

    override fun addProduct(product: Product) {
        productDao.addProduct(product)
    }

    override fun deleteProduct(productId: Long) {
        productDao.deleteProduct(productId)
    }

    override fun getAllProduct(): LiveData<List<Product>> {
        return productDao.getAllProducts()
    }

    override fun getProductItem(productId: Long): Product {
        return productDao.getProductItem(productId)
    }

    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = BuildConfig.apiKey,
    )

    override suspend fun getRecipe(): List<RecipeItem> {
        val list = productDao.getProductList()
        var prompt = PROMPT
        for (i in list) {
            prompt += i.name + " "
        }
        prompt += "Сделай это в формате Json, в котором будут лежать коллекция объектов рецептов, у которых будукт поля : id: Long, name: String, minTime: String. Мне нужен только Json"
        val response = generativeModel.generateContent(prompt).text.toString()
        val gson = Gson()
        val recipeItemListType = object : TypeToken<List<RecipeItem>>() {}.type
        val recipeItemList: List<RecipeItem> = gson.fromJson(response, recipeItemListType)
        return recipeItemList
    }

    companion object {
        private const val PROMPT = "Придумай мне рецепты из этих продуктов:"
    }
}