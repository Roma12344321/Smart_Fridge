package com.dev.smart_fridge.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import com.dev.smart_fridge.BuildConfig
import com.dev.smart_fridge.domain.Product
import com.dev.smart_fridge.domain.ProductRepository
import com.dev.smart_fridge.domain.RecipeItem
import com.google.ai.client.generativeai.GenerativeModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Calendar
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

    override fun getAllProduct(): LiveData<List<Product>> = MediatorLiveData<List<Product>>()
        .apply {
            addSource(productDao.getAllProducts()) {
                for (product in it) {
                    if (!isDateGreaterThanCurrent(product.time)) {
                        productDao.deleteProduct(product.id)
                    }
                }
                value = it
            }
        }

    private fun isDateGreaterThanCurrent(dateString: String): Boolean {
        return try {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val currentDate = Calendar.getInstance().time
            val compareDate = dateFormat.parse(dateString)

            compareDate?.after(currentDate) ?: false
        } catch (e: Exception) {
            false
        }
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
        prompt += JSON_PROMPT
        val response = parseData(generativeModel.generateContent(prompt).text.toString())
        Log.d("response", prompt)
        Log.d("response", response)
        val gson = Gson()
        val recipeItemListType = object : TypeToken<List<RecipeItem>>() {}.type
        val recipeItemList: List<RecipeItem> = gson.fromJson(response, recipeItemListType)
        return recipeItemList
    }

    override suspend fun getRecipeDetailInformation(name: String): String {
        val prompt = DETAIL_INFO_PROMPT + name
        val response = generativeModel.generateContent(prompt).text.toString()
        return response
    }

    private fun parseData(jsonString: String): String {
        val startIndex = jsonString.indexOf("[")
        val endIndex = jsonString.lastIndexOf("]")
        return jsonString.substring(startIndex, endIndex + 1)
    }

    companion object {
        private const val PROMPT = "Придумай мне рецепты из этих продуктов:"
        private const val JSON_PROMPT =
            ". Сделай это чётко с этими полями: id: Long, name: String, minTime: String. Напиши это в виде Json, но в виде одной строки завернув объекты в массив"
        private const val DETAIL_INFO_PROMPT = "Расскажи мне об этом рецепте: "
    }
}