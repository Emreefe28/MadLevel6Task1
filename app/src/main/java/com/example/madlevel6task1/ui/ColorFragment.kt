package com.example.madlevel6task1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel6task1.R
import com.example.madlevel6task1.databinding.FragmentColorBinding
import com.example.madlevel6task1.model.ColorItem
import com.example.madlevel6task1.viewmodel.ColorViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ColorFragment : Fragment() {
    private lateinit var fragmentColorBinding: FragmentColorBinding
    private val viewModel: ColorViewModel by viewModels()

    private val colors = arrayListOf<ColorItem>()
    private lateinit var colorAdapter: ColorAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentColorBinding = FragmentColorBinding.inflate(layoutInflater)
        return fragmentColorBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorAdapter = ColorAdapter(colors, this::onColorClick)
        fragmentColorBinding.rvColors.layoutManager =
                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        fragmentColorBinding.rvColors.adapter = colorAdapter

        observeColors()

    }

    private fun onColorClick(colorItem: ColorItem) {
        Snackbar.make(
                fragmentColorBinding.rvColors,
                "This color is ${colorItem.name}",
                Snackbar.LENGTH_LONG
        ).show()
    }

    private fun observeColors() {
        viewModel.colorItems.observe(viewLifecycleOwner) {
            colors.clear()
            colors.addAll(it)
            colorAdapter.notifyDataSetChanged()
        }
    }
}