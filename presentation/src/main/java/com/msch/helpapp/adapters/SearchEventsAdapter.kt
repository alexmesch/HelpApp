package com.msch.helpapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msch.helpapp.R
import com.msch.helpapp.domain.SearchInfo
import kotlinx.android.synthetic.main.sf_ve_recycler_item.view.*

class SearchEventsAdapter : RecyclerView.Adapter<SearchEventsAdapter.SearchViewHolder>() {

    private var searchItems: List<SearchInfo> = ArrayList()

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val searchResult = itemView.searchResult

        fun bind(searchItem: SearchInfo) {
            searchResult.text = searchItem.results
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.sf_ve_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchItems[position])
    }

    override fun getItemCount(): Int = searchItems.size

    fun submitList(searchList: List<SearchInfo>) {
        searchItems = searchList
    }
}
