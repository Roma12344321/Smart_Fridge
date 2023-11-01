package com.dev.smart_fridge.domain

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Single

interface ProductRepository {
    fun addProduct(product: Product)
    fun deleteProduct(productId: Long)
    fun getAllProduct(): LiveData<List<Product>>
    fun getProductItem(productId: Long): Product
}