package com.dev.smart_fridge.data

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import com.dev.smart_fridge.domain.Product

interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<Product>>

    @Insert
    fun addProduct(product: Product)

    @Query("DELETE FROM products WHERE id = :productId")
    fun deleteProduct(productId: Long)

    @Query("SELECT * FROM products WHERE id = :productId")
    fun getProductItem(productId: Long): Product

}