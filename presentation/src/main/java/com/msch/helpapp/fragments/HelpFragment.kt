package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msch.domain.model.CategoryItems
import com.msch.helpapp.*
import com.msch.helpapp.presenters.HelpPresenter
import com.msch.helpapp.views.HelpView
import com.msch.helpapp.adapters.CategoryViewAdapter
import com.msch.helpapp.dagger.HasComponent
import com.msch.helpapp.dagger.components.ActivityComponent
import com.msch.helpapp.dagger.components.DaggerFragmentComponent
import com.msch.helpapp.dagger.components.FragmentComponent
import com.msch.helpapp.dagger.modules.InteractorModule
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class HelpFragment: BaseFragment(), HelpView {

    @field: InjectPresenter
    @get: ProvidePresenter
    @Inject
    lateinit var helpPresenter: HelpPresenter
    private val adapter = CategoryViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (!::helpPresenter.isInitialized) {
            DaggerFragmentComponent.builder()
                .activityComponent(this.getActivityComponent(ActivityComponent::class.java))
                .interactorModule(InteractorModule())
                .build()
                .inject(this)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help_screen, container, false)
        adapter.setListener(object: CategoryViewAdapter.AdapterListener {
            override fun onItemClicked(position: Int, clickedId: Bundle) {
                val newsFragment = NewsFragment()
                newsFragment.arguments = clickedId

                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentView, newsFragment)
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        })

        helpPresenter.showCategories()
        switchLoadingScreen(view)
        return view
    }

    override fun displayCategories(categoryItems: List<CategoryItems>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = GridLayoutManager(requireActivity(), 2)
        adapter.submitList(categoryItems)
    }

    private fun switchLoadingScreen(view: View) {
        view.findViewById<FrameLayout>(R.id.hf_loadingScreen).visibility = GONE
    }
}