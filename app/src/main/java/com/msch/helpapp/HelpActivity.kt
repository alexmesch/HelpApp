package com.msch.helpapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.help_screen.*

class HelpActivity : AppCompatActivity() {
    private lateinit var categoryAdapter: CategoryViewAdapter

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.help_screen)

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet() {
        val data = DataSource.createDataSet()
        categoryAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = GridLayoutManager(this@HelpActivity, 2)
            categoryAdapter = CategoryViewAdapter()
            adapter = categoryAdapter
        }

        //recycler_view.layoutManager = GridLayoutManager(this@HelpActivity, 2)
        //categoryAdapter = CategoryViewAdapter()
        //recycler_view.adapter = categoryAdapter
    }

    fun exit(view : View) {
        finish()
    }
}