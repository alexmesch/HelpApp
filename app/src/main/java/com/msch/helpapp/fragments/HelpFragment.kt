package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.reflect.TypeToken
import com.msch.helpapp.*
import com.msch.helpapp.adapters.CategoryViewAdapter
import com.msch.helpapp.models.CategoryItems
import kotlinx.android.synthetic.main.fragment_help_screen.view.*
import kotlinx.coroutines.Dispatchers.IO
import com.msch.helpapp.concurrency.FileCoroutine.fileWorksThread
import com.msch.helpapp.concurrency.FileCoroutine.logThread
import kotlinx.coroutines.*

class HelpFragment : Fragment() {
    private val CATEGORIES = "categories"
    val listType = object : TypeToken<List<CategoryItems>>() {}.type
    private val lifecycleScope = MainScope()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help_screen, container, false)
        val categoryAdapter = CategoryViewAdapter()
        var data: List<CategoryItems> = ArrayList()
        val loadingScreen = view.findViewById<FrameLayout>(R.id.hf_loadingScreen)

        lifecycleScope.launch {
            async(IO) {
                data = fileWorksThread(requireActivity(), listType, CATEGORIES).filterIsInstance<CategoryItems>()
                //delay(500) //Для теста загрузочного экрана
            }.await()
            logThread("UI")
            view.recycler_view.adapter = categoryAdapter
            view.recycler_view.layoutManager = GridLayoutManager(requireActivity(), 2)
            categoryAdapter.submitList(data)
            loadingScreen.visibility = GONE
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        lifecycleScope.cancel()
    }
}