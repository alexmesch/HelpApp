package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.msch.domain.model.EventDetails
import com.msch.helpapp.R
import com.msch.helpapp.adapters.SearchEventsAdapter
import com.msch.helpapp.dagger.components.ActivityComponent
import com.msch.helpapp.dagger.components.DaggerFragmentComponent
import com.msch.helpapp.presenters.SearchPresenter
import com.msch.helpapp.views.SearchView
import kotlinx.android.synthetic.main.sf_viewpager_events_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class EventsSearchFragment: BaseFragment(), SearchView {
    private val searchAdapter = SearchEventsAdapter()
    private lateinit var items: Array<String>
    var listItems: ArrayList<String>? = null

    @field: InjectPresenter
    @get: ProvidePresenter
    @Inject
    lateinit var searchPresenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        if (!::searchPresenter.isInitialized) {
            DaggerFragmentComponent.builder()
                .activityComponent(this.getActivityComponent(ActivityComponent::class.java))
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
        val view = inflater.inflate(R.layout.sf_viewpager_events_fragment, container, false)
        searchPresenter.showAllEvents()
        return view
    }

    override fun showEvents(events: List<EventDetails>) {
        this.sf_ve_recycler.layoutManager = LinearLayoutManager(requireActivity())
        this.sf_ve_recycler.adapter = searchAdapter
        this.sf_ve_results_value.text = (events.lastIndex + 1).toString()
        searchAdapter.submitList(events)
    }
}