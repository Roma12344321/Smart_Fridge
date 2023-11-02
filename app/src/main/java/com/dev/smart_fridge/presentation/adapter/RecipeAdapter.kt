package com.dev.smart_fridge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dev.smart_fridge.R
import com.dev.smart_fridge.domain.RecipeItem

class RecipeAdapter : ListAdapter<RecipeItem, RecipeViewHolder>(RecipeItemDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecipeViewHolder, position: Int) {
        val recipeItem = getItem(position)
        viewHolder.textViewNameRecipe.text = recipeItem.name
        viewHolder.textViewMinTimeRecipe.text = recipeItem.minTime
    }
}