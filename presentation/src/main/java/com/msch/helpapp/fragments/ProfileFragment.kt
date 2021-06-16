package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.msch.data.model.UserProfile
import com.msch.helpapp.R
import com.msch.helpapp.presenters.UserPresenter
import com.msch.helpapp.views.UserView
import com.msch.helpapp.adapters.FriendsAdapter
import kotlinx.android.synthetic.main.fragment_profile_screen.*
import kotlinx.android.synthetic.main.fragment_profile_screen.view.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ProfileFragment : MvpAppCompatFragment(), UserView {
    private var profileScope = MainScope()

    @InjectPresenter(presenterId = "profilePresenter")
    lateinit var profilePresenter: UserPresenter

    @ProvidePresenter
    fun providePresenter(): UserPresenter {
        return UserPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_screen, container, false)
        lateinit var profileInfo: UserProfile
        providePresenter()


        profileScope.launch {
            withContext(IO) {
                profileInfo = profilePresenter.getUserInfo()
            }
            profilePresenter.showProfile(profileInfo)
            switchLoadingScreen(requireView())
        }
        view.pf_logout_button.setOnClickListener{ (profilePresenter.logOut(requireActivity().supportFragmentManager))}
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.pf_profile_image.setOnClickListener(this::openDialog)
    }

    override fun displayProfile(profile: UserProfile) {
        val friendsAdapter = FriendsAdapter()
        this.pf_profileName.text = profile.name
        this.pf_birthday_place.text = profile.birthday
        this.pf_occupation_place.text = profile.occupation

        this.pf_RecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        this.pf_RecyclerView.adapter = friendsAdapter
        //friendsAdapter.submitList(profile.friends)
    }

    private fun openDialog(view: View) {
        val dialogFragment = PFDialog()
        val manager = childFragmentManager
        dialogFragment.show(manager, "dialog")
    }

    private fun switchLoadingScreen(v: View) {
        v.pf_loading_screen.visibility = GONE
    }
}