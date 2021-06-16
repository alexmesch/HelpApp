package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msch.data.model.CategoryItems
import com.msch.helpapp.*
import com.msch.helpapp.presenters.HelpPresenter
import com.msch.helpapp.views.HelpView
import com.msch.helpapp.adapters.CategoryViewAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class HelpFragment : MvpAppCompatFragment(), HelpView {
    private val lifecycleScope = MainScope()
    @InjectPresenter(presenterId = "helpPresenter")
    lateinit var helpPresenter: HelpPresenter

    @ProvidePresenter
    fun providePresenter(): HelpPresenter {
        return HelpPresenter()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help_screen, container, false)
        var categories: List<CategoryItems> = ArrayList()
        providePresenter()

        lifecycleScope.launch {
            withContext(IO) {
                categories = helpPresenter.getCategories()
            }
            helpPresenter.showCategories(categories)
            switchLoadingScreen(view)
        }
        return view
    }

    override fun displayCategories(categoryItems: List<CategoryItems>) {
        val categoryAdapter = CategoryViewAdapter()
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView?.adapter = categoryAdapter
        recyclerView?.layoutManager = GridLayoutManager(requireActivity(), 2)
        categoryAdapter.submitList(categoryItems)
    }

    private fun switchLoadingScreen(view: View) {
        view.findViewById<FrameLayout>(R.id.hf_loadingScreen).visibility = GONE
    }
}