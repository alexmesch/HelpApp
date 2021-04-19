package com.msch.helpapp.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.msch.helpapp.R
import com.msch.helpapp.fragments.NewsFragment
import com.msch.helpapp.models.CategoryItems
import kotlinx.android.synthetic.main.hf_recycler_item.view.*

class CategoryViewAdapter : RecyclerView.Adapter<CategoryViewAdapter.CategoryViewHolder>() {
    private var items: List<CategoryItems> = ArrayList()

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryImage = itemView.category_image
        private val categoryName = itemView.category_title

        init {
            itemView.setOnClickListener {
                val newsFragment: Fragment = NewsFragment()
                val passInfo = Bundle()
                val fragmentManager = (itemView.context as AppCompatActivity).supportFragmentManager

                passInfo.putString("categoryID", items[position].categoryName)
                newsFragment.arguments = passInfo

                val transaction: FragmentTransaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentView, newsFragment)
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

        fun bind(categoryItem: CategoryItems, context: Context) {
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

    fun submitList(categoryList: List<CategoryItems>) {
        items = categoryList
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position], holder.itemView.context)
    }
}