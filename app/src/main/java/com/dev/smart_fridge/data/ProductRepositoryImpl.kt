package com.dev.smart_fridge.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.dev.smart_fridge.domain.Product
import com.dev.smart_fridge.domain.ProductRepository

import java.lang.RuntimeException

object ProductRepositoryImpl : ProductRepository {

    private val productDataBase: ProductDataBase
    private lateinit var application: Application

    fun initialize(application: Application) {
        this.application = application
    }

    init {
        productDataBase = ProductDataBase.getInstance(application)
    }

    override fun addProduct(product: Product) {
        productDataBase.productDao()?.addProduct(product)
    }

    override fun deleteProduct(productId: Long) {
        productDataBase.productDao()?.deleteProduct(productId)
    }

    override fun getAllProduct(): LiveData<List<Product>> {
        return productDataBase.productDao()?.getAllProducts()
            ?: throw RuntimeException("DataBaseError")
    }

    override fun getProductItem(productId: Long): Product {
        return productDataBase.productDao()?.getProductItem(productId)
            ?: throw RuntimeException("DataBaseError")
    }
}