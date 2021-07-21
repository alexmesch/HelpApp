package com.msch.helpapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.msch.domain.model.EventDetails
import com.msch.helpapp.EventDetailsActivity
import com.msch.helpapp.R
import com.msch.helpapp.presenters.NewsPresenter
import com.msch.helpapp.views.NewsView
import com.msch.helpapp.adapters.NewsAdapter
import com.msch.helpapp.dagger.components.ActivityComponent
import com.msch.helpapp.dagger.components.DaggerFragmentComponent
import kotlinx.android.synthetic.main.fragment_news_screen.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class NewsFragment : BaseFragment(), NewsView {
    private val id = "categoryID"
    private val newsAdapter = NewsAdapter()

    @field: InjectPresenter
    @get: ProvidePresenter
    @Inject
    lateinit var newsPresenter: NewsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        if (!::newsPresenter.isInitialized) {
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
        val view = inflater.inflate(R.layout.fragment_news_screen, container, false)
        val filter = arguments?.getString(id).toString()

        newsAdapter.setListener(object: NewsAdapter.AdapterListener {
            override fun onItemClicked(id: String?) {
                val intent = Intent(requireContext(), EventDetailsActivity::class.java)
                intent.putExtra("ID", id)
                requireContext().startActivity(intent)
            }
        })
        newsPresenter.showNews(filter)
        view.findViewById<FrameLayout>(R.id.nf_loadingScreen).visibility = GONE
        return view
    }

    override fun showNews(news: List<EventDetails>) {
        this.recycler_view.layoutManager = LinearLayoutManager(requireContext())
        this.recycler_view.adapter = newsAdapter
        newsAdapter.submitList(news)
    }
}