package com.dev.smart_fridge.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.dev.smart_fridge.domain.RecipeItem

class RecipeItemDiffCallBack : DiffUtil.ItemCallback<RecipeItem>() {
    override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
        return oldItem == newItem
    }
}