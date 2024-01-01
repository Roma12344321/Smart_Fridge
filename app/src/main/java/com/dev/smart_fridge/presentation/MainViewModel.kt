package com.dev.smart_fridge.presentation


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.smart_fridge.BuildConfig
import com.dev.smart_fridge.domain.AddProductUseCase
import com.dev.smart_fridge.domain.DeleteProductUseCase
import com.dev.smart_fridge.domain.GetAllProductUseCase
import com.dev.smart_fridge.domain.GetProductItemUseCase
import com.dev.smart_fridge.domain.Product
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val getAllProductUseCase: GetAllProductUseCase,
    private val getProductItemUseCase: GetProductItemUseCase,
) : ViewModel() {

   val liveData = MutableLiveData<String>()


    fun model(){

       CoroutineScope(Dispatchers.IO).launch {

           val generativeModel = GenerativeModel(
               modelName = "gemini-pro",
               apiKey = BuildConfig.apiKey,
           )

           val prompt = "Напиши 3 рецепта из курицы и выведи ответ в формате json"


           val response =  generativeModel.generateContent(prompt)
           liveData.postValue(response.text!!)

        }
    }






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