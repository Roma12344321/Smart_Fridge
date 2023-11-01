package com.dev.smart_fridge.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dev.smart_fridge.data.ProductRepositoryImpl
import com.dev.smart_fridge.domain.AddProductUseCase
import com.dev.smart_fridge.domain.DeleteProductUseCase
import com.dev.smart_fridge.domain.GetAllProductUseCase
import com.dev.smart_fridge.domain.GetProductItemUseCase
import com.dev.smart_fridge.domain.Product

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProductRepositoryImpl(application)
    private val addProductUseCase = AddProductUseCase(repository)
    private val deleteProductUseCase = DeleteProductUseCase(repository)
    private val getAllProductUseCase = GetAllProductUseCase(repository)
    private val getProductItemUseCase = GetProductItemUseCase(repository)

    fun addProduct(product: Product){
        addProductUseCase.addProduct(product)
    }
    fun deleteProduct(productId: Long){
        deleteProductUseCase.deleteProduct(productId)
    }
    fun getAllProduct():LiveData<List<Product>>{
        return getAllProductUseCase.getAllProduct()
    }
    fun getProductItem(productId: Long):Product{
        return getProductItemUseCase.getProductIem(productId)
    }
}