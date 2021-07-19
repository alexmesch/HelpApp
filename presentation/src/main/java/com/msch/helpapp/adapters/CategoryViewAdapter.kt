package com.msch.helpapp.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msch.helpapp.R
import kotlinx.android.synthetic.main.hf_recycler_item.view.*

class CategoryViewAdapter : RecyclerView.Adapter<CategoryViewAdapter.CategoryViewHolder>() {
    private var items: List<com.msch.domain.model.CategoryItems> = ArrayList()
    private val catId = "categoryID"

    private var listener: AdapterListener? = null

    interface AdapterListener {
        fun onItemClicked(position: Int, clickedId: Bundle)
    }

    fun setListener(listener: AdapterListener?) {
        this.listener = listener
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryImage = itemView.category_image
        private val categoryName = itemView.category_title

        init {
            itemView.setOnClickListener {
                val passInfo = Bundle()
                passInfo.putString(catId, items[adapterPosition].categoryName)

                listener?.onItemClicked(adapterPosition, passInfo)
            }
        }

        fun bind(categoryItem: com.msch.domain.model.CategoryItems, context: Context) {
            categoryName.setText(categoryItem.categoryName)
            categoryImage.setImageResource(
                context.resources.getIdentifier(
                    categoryItem.categoryImage,
                    "drawable",
                    context.packageName
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.hf_recycler_item, parent, false)
        )
    }

    override fun getItemCount() = items.size

    fun submitList(categoryList: List<com.msch.domain.model.CategoryItems>) {
        items = categoryList
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position], holder.itemView.context)
    }
}