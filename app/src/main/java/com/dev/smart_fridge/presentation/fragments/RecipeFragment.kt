package com.dev.smart_fridge.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dev.smart_fridge.databinding.FragmentRecipeBinding
import com.dev.smart_fridge.presentation.adapter.RecipeAdapter

class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding: FragmentRecipeBinding
        get() = _binding ?: throw RuntimeException("FragmentRecipeBinding is null")

    private val recipeAdapter by lazy {
        RecipeAdapter()
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
    }

    private fun setupRecycleView() {
        binding.recycleViewRecipe.adapter = recipeAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
