package com.dev.smart_fridge.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dev.smart_fridge.domain.Product

class ProductAdapter : ListAdapter<Product, ProductViewHolder>(ProductItemDiffCallBack()) {
    var onProductClickListener: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        viewHolder.textViewNameProduct.text = product.name
        viewHolder.textViewDataProduct.text = product.time
        viewHolder.view.setOnClickListener {
            onProductClickListener?.invoke(product)
        }
    }

    companion object {
        const val MAX_POOL = 25
    }
}