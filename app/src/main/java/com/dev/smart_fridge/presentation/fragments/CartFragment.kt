package com.dev.smart_fridge.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.smart_fridge.R
import com.dev.smart_fridge.databinding.FragmentCartBinding
import java.lang.RuntimeException

class CartFragment : Fragment() {

    private var _binding : FragmentCartBinding? = null
    private val binding : FragmentCartBinding
        get() = _binding ?: throw RuntimeException("FragmentCartBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

