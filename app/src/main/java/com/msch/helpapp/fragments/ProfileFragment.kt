package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.msch.helpapp.R
import com.msch.helpapp.adapters.FriendsAdapter
import com.msch.helpapp.data.FriendsSource
import kotlinx.android.synthetic.main.fragment_profile_screen.view.*

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile_screen, container, false)
        val friendsAdapter = FriendsAdapter()
        val data = FriendsSource.createFriendData()

        view.pf_RecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        view.pf_RecyclerView.adapter = friendsAdapter
        friendsAdapter.submitList(data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.pf_profile_image.setOnClickListener(this::openDialog)
    }

    private fun openDialog(view: View) {
        val dialogFragment = PFDialog()
        val manager = childFragmentManager
        dialogFragment.show(manager,"dialog")
    }
}