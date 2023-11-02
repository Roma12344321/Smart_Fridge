package com.dev.smart_fridge.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dev.smart_fridge.R
import com.dev.smart_fridge.presentation.adapter.ProductAdapter
import com.dev.smart_fridge.presentation.adapter.RecipeAdapter

class RecipeFragment : Fragment() {

    private lateinit var recipeAdapter:RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView(view)
    }
    private fun setupRecycleView(view: View) {
        val recyclerViewRecipe = view.findViewById<RecyclerView>(R.id.recycleViewRecipe)
        recipeAdapter = RecipeAdapter()
        recyclerViewRecipe.adapter = recipeAdapter
    }
}
