package com.dev.smart_fridge.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.dev.smart_fridge.domain.Product
import com.dev.smart_fridge.domain.ProductRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Flow.Subscriber

class ProductRepositoryImpl(application: Application) : ProductRepository {

    private val productDataBase = ProductDataBase.getInstance(application)
    override fun addProduct(product: Product) {
        productDataBase.productDao().addProduct(product)
    }

    override fun deleteProduct(productId: Long) {
        productDataBase.productDao().deleteProduct(productId)
    }

    override fun getAllProduct(): LiveData<List<Product>> {
        return productDataBase.productDao().getAllProducts()
    }

    override fun getProductItem(productId: Long): Product {
        return productDataBase.productDao().getProductItem(productId)
    }
}