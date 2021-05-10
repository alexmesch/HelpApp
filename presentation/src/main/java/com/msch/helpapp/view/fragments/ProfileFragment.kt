package com.msch.helpapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.DI.DependencyInjectorImpl
import com.msch.helpapp.R
import com.msch.helpapp.view.adapters.FriendsAdapter
import com.msch.helpapp.view.fragments.FragmentsManager.openFragment
import com.msch.helpapp.domain.UserProfile
import com.msch.helpapp.presenter.UserPresenter
import com.msch.helpapp.view.contracts.UserInfoContract
import kotlinx.android.synthetic.main.fragment_profile_screen.*
import kotlinx.android.synthetic.main.fragment_profile_screen.view.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragment : Fragment(), UserInfoContract.UserView {
    private val userID = Firebase.auth.currentUser!!.uid
    private lateinit var presenter: UserInfoContract.UserPresenter
    private var profileScope = MainScope()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_screen, container, false)
        lateinit var profileInfo: UserProfile
        setPresenter(UserPresenter(this, DependencyInjectorImpl()))
        profileScope.launch {
            withContext(IO) {
                profileInfo = presenter.getUserInfo(userID)
            }
            displayProfile(profileInfo)
            switchLoadingScreen(requireView())
        }

        val logoutBtn = view.pf_logout_button
        logoutBtn.setOnClickListener { logOut() }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.pf_profile_image.setOnClickListener(this::openDialog)
    }

    override fun setPresenter(presenter: UserInfoContract.UserPresenter) {
        this.presenter = presenter
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

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        profileScope.cancel()
    }

    private fun logOut() {
        Firebase.auth.signOut()
        activity?.supportFragmentManager?.popBackStack()
        openFragment(AuthFragment(), requireFragmentManager())
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