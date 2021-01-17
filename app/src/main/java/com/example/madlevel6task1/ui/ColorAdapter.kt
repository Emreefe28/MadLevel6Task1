package com.example.madlevel6task1.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madlevel6task1.databinding.ItemColorBinding
import com.example.madlevel6task1.model.ColorItem
import kotlinx.android.synthetic.main.item_color.view.*

class ColorAdapter(private val colors: List<ColorItem>, private val onClick: (ColorItem) -> Unit) :
    RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemColorBinding =
            ItemColorBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(itemColorBinding)

    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(colors[position])

    inner class ViewHolder(private val itemColorBinding: ItemColorBinding) :
        RecyclerView.ViewHolder(itemColorBinding.root) {

        init {
            // Attach click listener to the ImageView and execute the onClick function when event occurs
            itemColorBinding.ivColor.setOnClickListener {
                onClick(colors[adapterPosition])
            }
        }

        fun bind(colorItem: ColorItem) {
            Glide.with(context).load(colorItem.getImageUrl())
                .into(itemColorBinding.ivColor);
        }
    }
}
