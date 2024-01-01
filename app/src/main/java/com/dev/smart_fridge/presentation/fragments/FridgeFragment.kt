package com.dev.smart_fridge.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dev.smart_fridge.databinding.FragmentFridgeBinding
import com.dev.smart_fridge.domain.Product
import com.dev.smart_fridge.presentation.FridgeApp
import com.dev.smart_fridge.presentation.MainViewModel
import com.dev.smart_fridge.presentation.ViewModelFactory
import com.dev.smart_fridge.presentation.adapter.ProductAdapter
import javax.inject.Inject

class FridgeFragment : Fragment() {

    private var _binding: FragmentFridgeBinding? = null
    private val binding: FragmentFridgeBinding
        get() = _binding ?: throw RuntimeException("FragmentFridgeBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }
    private val productAdapter by lazy {
        ProductAdapter()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as FridgeApp).component
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFridgeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecycleView()
        binding.floatingActionButton.setOnClickListener {
            val dialog = AddProductDialogFragment()
            dialog.listener = object : AddProductDialogFragment.AddProductDialogListener {
                override fun onDialogPositiveClick(productName: String, expiryDate: String) {
                    val product = Product(productName, expiryDate)
                    viewModel.addProduct(product)
                }
            }
            dialog.show(parentFragmentManager, "AddProductDialogFragment")
        }
        viewModel.product.observe(viewLifecycleOwner) {
            productAdapter.submitList(it)
        }
    }

    private fun setupRecycleView() {
        binding.recycleViewProduct.adapter = productAdapter
        setupSwipe(binding.recycleViewProduct)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
