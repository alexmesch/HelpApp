package com.msch.helpapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msch.domain.model.CategoryItems
import com.msch.helpapp.*
import com.msch.helpapp.presenters.HelpPresenter
import com.msch.helpapp.views.HelpView
import com.msch.helpapp.adapters.CategoryViewAdapter
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class HelpFragment: MvpAppCompatFragment(), HelpView {
    private var disposables = CompositeDisposable()

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
        providePresenter()

        helpPresenter.getObservable().subscribe(object : SingleObserver<List<CategoryItems>> {
            override fun onSubscribe(d: Disposable) {
                disposables.add(d)
            }

            override fun onSuccess(t: List<CategoryItems>) {
                //Log.d("hp", t.toString())
                helpPresenter.showCategories(t)
                switchLoadingScreen(view)
                disposables.clear()
            }

            override fun onError(e: Throwable) {
                Log.e("HelpFragmentSubscriber", "subscription fail!")
                e.stackTrace
            }
        })
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