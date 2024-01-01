package com.dev.smart_fridge.data

import androidx.lifecycle.LiveData
import com.dev.smart_fridge.BuildConfig
import com.dev.smart_fridge.domain.Product
import com.dev.smart_fridge.domain.ProductRepository
import com.google.ai.client.generativeai.GenerativeModel
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

    override suspend fun getRecipe(promt: String): String {
        val response = generativeModel.generateContent(promt)
        return response.text.toString()
    }
}