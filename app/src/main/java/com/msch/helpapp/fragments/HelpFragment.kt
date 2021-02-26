package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.reflect.TypeToken
import com.msch.helpapp.*
import com.msch.helpapp.adapters.CategoryViewAdapter
import com.msch.helpapp.models.CategoryItems
import kotlinx.android.synthetic.main.fragment_help_screen.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import com.msch.helpapp.concurrency.FileCoroutine.fileWorksThread

class HelpFragment : Fragment() {
    private val CATEGORIES ="categories"
    val listType = object : TypeToken<List<CategoryItems>>(){}.type

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help_screen, container, false)
        val categoryAdapter = CategoryViewAdapter()

        view.recycler_view.layoutManager = GridLayoutManager(requireActivity(), 2)
        view.recycler_view.adapter = categoryAdapter

        //Есть следуюющие CoroutineScopes: IO, Main и Default
        CoroutineScope(IO).launch{
            categoryAdapter.submitList(fileWorksThread(requireActivity(), listType, CATEGORIES).filterIsInstance<CategoryItems>())
        }
        return view
    }
}