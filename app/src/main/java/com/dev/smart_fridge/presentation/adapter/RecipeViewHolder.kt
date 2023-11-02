package com.dev.smart_fridge.presentation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.smart_fridge.R

class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textViewNameRecipe = view.findViewById<TextView>(R.id.textViewNameRecipe)
    val textViewMinTimeRecipe = view.findViewById<TextView>(R.id.textViewMinTimeRecipe)
}