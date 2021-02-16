package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.msch.helpapp.*
import com.msch.helpapp.adapters.CategoryViewAdapter
import kotlinx.android.synthetic.main.fragment_help_screen.view.*
import com.msch.helpapp.objects.JsonParser.openFile
import com.msch.helpapp.objects.JsonParser.parseCategoriesJson

class HelpFragment : Fragment() {
    private val CATEGORIES ="categories"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fileContent: String = openFile(requireActivity().assets.open(CATEGORIES))
        val categoriesData = parseCategoriesJson(fileContent)

        val view = inflater.inflate(R.layout.fragment_help_screen, container, false)
        val categoryAdapter = CategoryViewAdapter()

        view.recycler_view.layoutManager = GridLayoutManager(requireActivity(), 2)
        view.recycler_view.adapter = categoryAdapter
        categoryAdapter.submitList(categoriesData)

        return view
    }
}