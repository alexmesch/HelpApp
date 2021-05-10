package com.msch.helpapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msch.helpapp.*
import com.msch.helpapp.DI.DependencyInjectorImpl
import com.msch.helpapp.view.adapters.CategoryViewAdapter
import com.msch.helpapp.domain.CategoryItems
import com.msch.helpapp.presenter.CategoryPresenter
import com.msch.helpapp.view.contracts.CategoryContract
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class HelpFragment : Fragment(), CategoryContract.CategoryView {
    private lateinit var presenter: CategoryContract.CategoryPresenter
    private val lifecycleScope = MainScope()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help_screen, container, false)
        var data: List<CategoryItems>
        setPresenter(CategoryPresenter(this, DependencyInjectorImpl()))
        lifecycleScope.launch {
            withContext(IO) {
                data = presenter.getCategories()
            }
            displayCategories(data)
            switchLoadingScreen(view)
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        lifecycleScope.cancel()
    }

    override fun displayCategories(data: List<CategoryItems>) {
        val categoryAdapter = CategoryViewAdapter()
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView?.adapter = categoryAdapter
        recyclerView?.layoutManager = GridLayoutManager(requireActivity(), 2)
        categoryAdapter.submitList(data)
    }

    override fun setPresenter(presenter: CategoryContract.CategoryPresenter) {
        this.presenter = presenter
    }

    private fun switchLoadingScreen(view: View) {
        view.findViewById<FrameLayout>(R.id.hf_loadingScreen).visibility = GONE
    }
}