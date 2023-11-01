package com.dev.smart_fridge.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.smart_fridge.R

class ProductViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val textViewNameProduct = view.findViewById<TextView>(R.id.textViewNameProduct)
    val textViewDataProduct = view.findViewById<TextView>(R.id.textViewDataProduct)
}