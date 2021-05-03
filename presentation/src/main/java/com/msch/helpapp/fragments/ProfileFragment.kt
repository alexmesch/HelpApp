package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.R
import com.msch.helpapp.adapters.FriendsAdapter
import com.msch.helpapp.data.EventDetailsRepo
import com.msch.helpapp.fragments.FragmentsManager.openFragment
import com.msch.helpapp.domain.UserProfile
import com.msch.helpapp.implementations.*
import com.msch.helpapp.interactors.*
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_profile_screen.view.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileFragment : Fragment() {
    private val userID = Firebase.auth.currentUser!!.uid
    private lateinit var profileInfo: UserProfile
    private var disposables: CompositeDisposable = CompositeDisposable()
    @Inject
    lateinit var interactors: Interactors

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_screen, container, false)
        val friendsAdapter = FriendsAdapter()
        interactors = Interactors(
            AddEvents(EventDetailsRepo(EventDetailsDsImpl())),
            FilterNews(FilterNewsImpl()),
            AddCategories(CategoryItemsDsImpl()),
            GetUserInfo(GetUserInfoImpl()),
            GenerateSearchResults(SearchResultsGenerator()),
            FirebaseLogin(FirebaseLoginImpl()),
            FirebaseReg(FirebaseRegImpl())
        )
        MainScope().launch {
            withContext(IO) {
                profileInfo = interactors.getUserInfo(userID)
            }

            val logoutBtn = view.pf_logout_button
            view.pf_profileName.text = profileInfo.name
            view.pf_birthday_place.text = profileInfo.birthday
            view.pf_occupation_place.text = profileInfo.occupation

            view.pf_RecyclerView.layoutManager = LinearLayoutManager(requireActivity())
            view.pf_RecyclerView.adapter = friendsAdapter
            //friendsAdapter.submitList(profileInfo.friends)

            logoutBtn.setOnClickListener {
                Firebase.auth.signOut()
                activity?.supportFragmentManager?.popBackStack()
                openFragment(AuthFragment(), requireFragmentManager())
            }
            view.pf_loading_screen.visibility = GONE
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.pf_profile_image.setOnClickListener(this::openDialog)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    private fun openDialog(view: View) {
        val dialogFragment = PFDialog()
        val manager = childFragmentManager
        dialogFragment.show(manager, "dialog")
    }
}