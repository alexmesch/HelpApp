package com.msch.helpapp.adapters

import android.content.ContentProvider
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.msch.helpapp.EventDetailsActivity
import com.msch.helpapp.MainActivity
import com.msch.helpapp.R
import com.msch.helpapp.fragments.NewsFragment
import com.msch.helpapp.models.CategoryItems
import kotlinx.android.synthetic.main.hf_recycler_item.view.*
import java.security.AccessController.getContext

class CategoryViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<CategoryItems> = ArrayList()

    inner class CategoryViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImage = itemView.category_image
        val categoryName = itemView.category_title

        fun bind(categoryItem: CategoryItems, context: Context) {
            categoryName.setText(categoryItem.categoryName)
            categoryImage.setImageResource(context.resources.getIdentifier(categoryItem.categoryImage,"drawable",context.packageName))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.hf_recycler_item, parent, false)

        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder) {
            is CategoryViewHolder -> {
                holder.bind(items[position], holder.itemView.context)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(categoryList : List<CategoryItems>) {
        items = categoryList
    }
}