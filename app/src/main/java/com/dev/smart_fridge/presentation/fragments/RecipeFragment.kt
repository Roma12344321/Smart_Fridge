package com.dev.smart_fridge.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dev.smart_fridge.databinding.FragmentRecipeBinding
import com.dev.smart_fridge.presentation.FridgeApp
import com.dev.smart_fridge.presentation.MainViewModel
import com.dev.smart_fridge.presentation.ViewModelFactory
import com.dev.smart_fridge.presentation.adapter.RecipeAdapter
import javax.inject.Inject

class RecipeFragment : Fragment() {

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
        viewModel.recipesLiveData.observe(viewLifecycleOwner) {
            recipeAdapter.submitList(it)
        }
        recipeAdapter.onRecipeClickListener = object : RecipeAdapter.OnRecipeClickListener{
            override fun onRecipeClick(name: String) {
                val intent = RecipeDetailInformationActivity.newInstance(requireContext(),name)
                startActivity(intent)
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
