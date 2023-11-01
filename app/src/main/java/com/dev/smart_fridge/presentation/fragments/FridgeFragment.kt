package com.dev.smart_fridge.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dev.smart_fridge.R
import com.dev.smart_fridge.presentation.MainViewModel
import com.dev.smart_fridge.presentation.ProductAdapter

class FridgeFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var productAdapter: ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fridge, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecycleView(view)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getAllProduct().observe(viewLifecycleOwner) {
            productAdapter.submitList(it)
        }
    }
    private fun setupRecycleView(view: View) {
        val recyclerViewProduct = view.findViewById<RecyclerView>(R.id.recycleViewProduct)
        productAdapter = ProductAdapter()
        recyclerViewProduct.adapter = productAdapter
        setupSwipe(recyclerViewProduct)
    }

    private fun setupSwipe(recyclerViewProduct: RecyclerView?) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = productAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteProduct(item.id)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerViewProduct)
    }
}
