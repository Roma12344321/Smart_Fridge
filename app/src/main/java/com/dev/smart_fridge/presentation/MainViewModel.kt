package com.dev.smart_fridge.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.smart_fridge.domain.AddProductUseCase
import com.dev.smart_fridge.domain.DeleteProductUseCase
import com.dev.smart_fridge.domain.GetAllProductUseCase
import com.dev.smart_fridge.domain.GetProductItemUseCase
import com.dev.smart_fridge.domain.GetRecipeDetailInformationUseCase
import com.dev.smart_fridge.domain.GetRecipeUseCase
import com.dev.smart_fridge.domain.Product
import com.dev.smart_fridge.domain.RecipeItem
import com.google.ai.client.generativeai.type.ServerException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val getAllProductUseCase: GetAllProductUseCase,
    private val getProductItemUseCase: GetProductItemUseCase,
    private val getRecipeUseCase: GetRecipeUseCase,
    private val getRecipeDetailInformationUseCase: GetRecipeDetailInformationUseCase
) : ViewModel() {

    private val _recipesLiveData = MutableLiveData<List<RecipeItem>>()
    val recipesLiveData: LiveData<List<RecipeItem>>
        get() = _recipesLiveData

    private val _animation = MutableLiveData<Boolean>()
    val animation: LiveData<Boolean>
        get() = _animation

    private val _recipeDetailInfo = MutableLiveData<String>()
    val recipeDetailInfo: LiveData<String>
        get() = _recipeDetailInfo

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar: LiveData<Boolean>
        get() = _showProgressBar

    private val _showCountryError = MutableLiveData<Boolean>()
    val showCountryError: LiveData<Boolean>
        get() = _showCountryError

    private val scope = CoroutineScope(Dispatchers.Main)

    fun getRecipes() {
        scope.launch {
            try {
                _recipesLiveData.value = mutableListOf()
                _showProgressBar.value = true
                val response = withContext(Dispatchers.IO) {
                    getRecipeUseCase.getRecipe()
                }

                _showProgressBar.value = false
                delay(400)
                _recipesLiveData.value = response
            } catch (_: ServerException) {
                _showCountryError.value = true
            } catch (_: Exception) {
            }
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

    fun getRecipeDetailInformation(name: String) {
        scope.launch {
            try {
                _showProgressBar.value = true
                val response = withContext(Dispatchers.IO) {
                    getRecipeDetailInformationUseCase.getRecipeDetailInformation(name)
                }
                _recipeDetailInfo.value = response
                _showProgressBar.value = false
            } catch (_: ServerException) {
                _showCountryError.value = true
            } catch (_: Exception) {
            }
        }
    }

    fun shouldBeDelayed() {
        scope.launch {
            _animation.value = true
            delay(1000)
            _animation.value = false
        }
    }


    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }


}