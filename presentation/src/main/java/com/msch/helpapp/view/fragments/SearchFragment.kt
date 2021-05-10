package com.msch.helpapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.msch.helpapp.R
import com.msch.helpapp.view.adapters.SearchPagerAdapter
import kotlinx.android.synthetic.main.fragment_search_screen.*

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_screen,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabs()
    }

    private fun setUpTabs() {
        val searchPageAdapter = SearchPagerAdapter(childFragmentManager)
        searchPageAdapter.addFragment(EventsSearchFragment(), getString(R.string.sf_tab_title_events))
        searchPageAdapter.addFragment(NKOSearchFragment(), getString(R.string.sf_tab_title_NKO))
        sf_viewPager.adapter = searchPageAdapter
        sf_tabs.setupWithViewPager(sf_viewPager)
    }
}