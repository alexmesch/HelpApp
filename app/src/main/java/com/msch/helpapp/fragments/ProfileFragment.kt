package com.msch.helpapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.R
import com.msch.helpapp.adapters.FriendsAdapter
import com.msch.helpapp.database.FirebaseOperations.retrieveUserInformation
import com.msch.helpapp.fragments.FragmentsManager.openFragment
import com.msch.helpapp.models.UserProfile
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_profile_screen.view.*

class ProfileFragment : Fragment() {
    private val firebaseChild = Firebase.auth.currentUser!!.uid
    private lateinit var profileInfo: UserProfile
    private var disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_screen, container, false)
        val friendsAdapter = FriendsAdapter()
        val fbRef = Firebase.database.reference

        val logoutBtn = view.pf_logout_button

        fbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val profileName = view.pf_profileName
                val profileBirthday = view.pf_birthday_place
                val profileOccup = view.pf_occupation_place

                val profileObservable: Observable<UserProfile> = Observable
                    .just(retrieveUserInformation(dataSnapshot, firebaseChild))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(mainThread())

                profileObservable.subscribe(object : Observer<UserProfile> {
                    override fun onSubscribe(d: Disposable) {
                        Log.d("Auth", "onSubscribe: called.")
                        disposables.add(d)
                    }

                    override fun onNext(task: UserProfile) {
                        profileInfo = UserProfile(task.birthday, task.friends, task.name, task.occupation, task.profilePic)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("rx", "onError:", e)
                    }
                    override fun onComplete() {
                        profileName.text = profileInfo.name
                        profileBirthday.text = profileInfo.birthday
                        profileOccup.text = profileInfo.occupation
                    }
                })
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("Firebase", "Load: cancelled", databaseError.toException())
            }

        })
        view.pf_RecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        view.pf_RecyclerView.adapter = friendsAdapter
        //friendsAdapter.submitList(profileInfo.friends)

        logoutBtn.setOnClickListener {
            Firebase.auth.signOut()
            activity?.supportFragmentManager?.popBackStack()
            openFragment(AuthFragment(), this.requireFragmentManager())
        }
        val loadingScreen = view.pf_loading_screen
        loadingScreen.visibility = GONE

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