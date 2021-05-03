package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.R
import com.msch.helpapp.adapters.NewsAdapter
import com.msch.helpapp.data.EventDetailsRepo
import com.msch.helpapp.domain.EventDetails
import com.msch.helpapp.implementations.*
import com.msch.helpapp.interactors.*
import kotlinx.android.synthetic.main.fragment_news_screen.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject

class NewsFragment : Fragment() {
    private val lifecycleScope = MainScope()
    @Inject
    lateinit var interactors: Interactors

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_screen, container, false)
        val newsAdapter = NewsAdapter()
        val loadingScreen = view.findViewById<FrameLayout>(R.id.nf_loadingScreen)
        lateinit var data: List<EventDetails>
        lateinit var filteredData: List<EventDetails>

        lifecycleScope.launch {
            interactors = Interactors(
                AddEvents(EventDetailsRepo(EventDetailsDsImpl())),
                FilterNews(FilterNewsImpl()),
                AddCategories(CategoryItemsDsImpl()),
                GetUserInfo(GetUserInfoImpl()),
                GenerateSearchResults(SearchResultsGenerator()),
                FirebaseLogin(FirebaseLoginImpl()),
                FirebaseReg(FirebaseRegImpl())
            )
            withContext(IO) {
                data = interactors.addEvents()
                filteredData = interactors.filterNews(data, arguments?.getString("categoryID"))
            }
            view.recycler_view.layoutManager = LinearLayoutManager(requireActivity())
            view.recycler_view.adapter = newsAdapter
            newsAdapter.submitList(filteredData)
            loadingScreen.visibility = GONE
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        lifecycleScope.cancel()
    }
}