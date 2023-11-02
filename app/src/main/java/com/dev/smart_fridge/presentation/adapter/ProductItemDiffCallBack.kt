package com.dev.smart_fridge.presentation.adapter


import androidx.recyclerview.widget.DiffUtil
import com.dev.smart_fridge.domain.Product

class ProductItemDiffCallBack : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}