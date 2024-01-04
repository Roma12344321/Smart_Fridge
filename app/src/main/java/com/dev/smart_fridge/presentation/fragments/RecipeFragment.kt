package com.dev.smart_fridge.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dev.smart_fridge.R
import com.dev.smart_fridge.databinding.FragmentRecipeBinding
import com.dev.smart_fridge.presentation.FridgeApp
import com.dev.smart_fridge.presentation.MainViewModel
import com.dev.smart_fridge.presentation.ViewModelFactory
import com.dev.smart_fridge.presentation.adapter.RecipeAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RecipeFragment : Fragment() {

    private val scope = CoroutineScope(Dispatchers.Main)

    private var _binding: FragmentRecipeBinding? = null
    private val binding: FragmentRecipeBinding
        get() = _binding ?: throw RuntimeException("FragmentRecipeBinding is null")

    private val recipeAdapter by lazy {
        RecipeAdapter()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as FridgeApp).component
    }

    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        viewModel.getRecipes()
        viewModel.animation.observe(viewLifecycleOwner) {
            if (it) {
                binding.imageViewFridge.visibility = View.GONE
            }
        }
        viewModel.recipesLiveData.observe(viewLifecycleOwner) {
            recipeAdapter.submitList(it)
        }
        viewModel.showProgressBar.observe(viewLifecycleOwner) {
            if (it) {
                binding.imageViewFridge.visibility = View.VISIBLE
                binding.imageViewFridge.setImageResource(R.drawable.ic_fridge_close)
                binding.imageViewFridge.animation = AnimationUtils.loadAnimation(context, R.anim.shake)
            }
            else{
                binding.imageViewFridge.clearAnimation()
                binding.imageViewFridge.setImageResource(R.drawable.ic_fridge_close)
                viewModel.shouldBeDelayed()
            }
        }
        recipeAdapter.onRecipeClickListener = object : RecipeAdapter.OnRecipeClickListener{
            override fun onRecipeClick(name: String) {
                val intent = RecipeDetailInformationActivity.newInstance(requireContext(),name)
                startActivity(intent)
            }
        }
        viewModel.showCountryError.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context,"Извините, доступно только в США",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecycleView() {
        binding.recycleViewRecipe.adapter = recipeAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
