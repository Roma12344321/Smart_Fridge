package com.dev.smart_fridge.presentation.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dev.smart_fridge.databinding.ActivityRecipeDetailInformationBinding
import com.dev.smart_fridge.presentation.FridgeApp
import com.dev.smart_fridge.presentation.MainViewModel
import com.dev.smart_fridge.presentation.ViewModelFactory
import javax.inject.Inject

class RecipeDetailInformationActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory : ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
    }

    private val component by lazy {
        (application as FridgeApp).component
    }

    private val binding by lazy {
        ActivityRecipeDetailInformationBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val recipe = intent.getStringExtra(KEY)
        if (recipe != null) {
            viewModel.getRecipeDetailInformation(recipe)
        }
        viewModel.recipeDetailInfo.observe(this) {
            binding.textViewName.text = recipe
            binding.textViewDescription.text = it.description
            binding.textViewIngredients.text = it.ingredients
            binding.textViewCookingMethod.text = it.cookingMethod
        }
        viewModel.showProgressBar.observe(this) {
            if (it) {
                binding.progressBarDetailInfo.visibility = View.VISIBLE
                binding.cardViewDescription.visibility = View.GONE
                binding.cardViewIngredients.visibility = View.GONE
                binding.cardViewCookingMethod.visibility = View.GONE
            }
            else {
                binding.progressBarDetailInfo.visibility = View.GONE
                binding.cardViewDescription.visibility = View.VISIBLE
                binding.cardViewIngredients.visibility = View.VISIBLE
                binding.cardViewCookingMethod.visibility = View.VISIBLE
            }
        }
    }

    companion object {

        private const val KEY = "RECIPE"

        fun newInstance(context:Context, recipe : String) : Intent {
            return Intent(context,RecipeDetailInformationActivity::class.java).apply {
                putExtra(KEY,recipe)
            }
        }
    }
}