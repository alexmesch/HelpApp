package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.msch.helpapp.R
import com.msch.helpapp.adapters.SearchPagerAdapter
import kotlinx.android.synthetic.main.fragment_search_screen.*

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_screen,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabs()
    }

    private fun setUpTabs() {
        val searchPageAdapter = SearchPagerAdapter(childFragmentManager)
        searchPageAdapter.addFragment(EventsSearchPage(), "По мероприятиям")
        searchPageAdapter.addFragment(NKOSearchPage(), "По НКО")
        sf_viewPager.adapter = searchPageAdapter
        sf_tabs.setupWithViewPager(sf_viewPager)
    }
}