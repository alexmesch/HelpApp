package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.msch.helpapp.*
import com.msch.helpapp.adapters.CategoryViewAdapter
import com.msch.helpapp.data.EventDetailsRepo
import com.msch.helpapp.domain.CategoryItems
import com.msch.helpapp.implementations.*
import com.msch.helpapp.interactors.*
import kotlinx.android.synthetic.main.fragment_help_screen.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject

class HelpFragment : Fragment() {
    private val lifecycleScope = MainScope()

    @Inject
    lateinit var interactors: Interactors

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help_screen, container, false)
        val categoryAdapter = CategoryViewAdapter()
        var data: List<CategoryItems>
        val loadingScreen = view.findViewById<FrameLayout>(R.id.hf_loadingScreen)

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
                data = interactors.addCategories()
            }
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