package com.dev.smart_fridge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dev.smart_fridge.R
import com.dev.smart_fridge.domain.Product

class ProductAdapter : ListAdapter<Product, ProductViewHolder>(ProductItemDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        viewHolder.textViewNameProduct.text = product.name
        viewHolder.textViewDataProduct.text = product.time
    }
}