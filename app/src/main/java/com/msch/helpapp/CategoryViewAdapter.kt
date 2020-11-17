package com.msch.helpapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.msch.helpapp.models.CategoryItem
import kotlinx.android.synthetic.main.recycler_item.view.*

class CategoryViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<CategoryItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is CategoryViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(categoryList : List<CategoryItem>) {
        items = categoryList
    }

    class CategoryViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImage = itemView.category_image
        val categoryTitle = itemView.category_title


        fun bind(categoryItem: CategoryItem) {
            categoryTitle.setText(categoryItem.title)
            categoryImage.setImageResource(categoryItem.imageId)
        }
    }
}