package com.msch.helpapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.msch.domain.model.UserProfile
import com.msch.helpapp.R
import com.msch.helpapp.presenters.UserPresenter
import com.msch.helpapp.views.UserView
import com.msch.helpapp.adapters.FriendsAdapter
import com.msch.helpapp.dagger.components.DaggerDataComponent
import com.msch.helpapp.dagger.components.DaggerFragmentManagerComponent
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_profile_screen.*
import kotlinx.android.synthetic.main.fragment_profile_screen.view.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ProfileFragment : MvpAppCompatFragment(), UserView {
    private var disposables = CompositeDisposable()
    private val fmComponent = DaggerFragmentManagerComponent.create()
    private val udComponent = DaggerDataComponent.create()

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
        providePresenter()

        profilePresenter.getObservable(udComponent)
            .subscribe(object: SingleObserver<UserProfile> {
            override fun onSubscribe(d: Disposable) {
                disposables.add(d)
            }

            override fun onSuccess(t: UserProfile) {
                profilePresenter.showProfile(t)
                disposables.clear()
                view.pf_loading_screen.visibility = GONE
            }

            override fun onError(e: Throwable) {
                Log.e("pfObserver", "subscription fail!")
                e.stackTrace
            }
        })
        view.pf_logout_button.setOnClickListener{ (profilePresenter.logOut(fmComponent, requireActivity().supportFragmentManager))}
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
}