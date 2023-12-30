package com.dev.smart_fridge.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dev.smart_fridge.data.ProductRepositoryImpl
import com.dev.smart_fridge.domain.AddProductUseCase
import com.dev.smart_fridge.domain.DeleteProductUseCase
import com.dev.smart_fridge.domain.GetAllProductUseCase
import com.dev.smart_fridge.domain.GetProductItemUseCase
import com.dev.smart_fridge.domain.Product
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val getAllProductUseCase: GetAllProductUseCase,
    private val getProductItemUseCase: GetProductItemUseCase,
) : ViewModel() {


    fun addProduct(product: Product) {
        addProductUseCase.addProduct(product)
    }

    fun deleteProduct(productId: Long) {
        deleteProductUseCase.deleteProduct(productId)
    }

    val product = getAllProductUseCase.getAllProduct()
    fun getProductItem(productId: Long): Product {
        return getProductItemUseCase.getProductIem(productId)
    }
}