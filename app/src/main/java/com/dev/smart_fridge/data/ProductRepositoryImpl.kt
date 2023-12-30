package com.dev.smart_fridge.data

import androidx.lifecycle.LiveData
import com.dev.smart_fridge.domain.Product
import com.dev.smart_fridge.domain.ProductRepository
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
}