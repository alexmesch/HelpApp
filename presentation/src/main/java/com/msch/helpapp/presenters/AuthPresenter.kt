package com.msch.helpapp.presenters

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.AuthResult
import com.msch.data.network.FirebaseOps
import com.msch.helpapp.fragments.FragmentsManager
import com.msch.helpapp.views.AuthView
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_auth_screen.view.*
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class AuthPresenter
@Inject constructor(
    private var fragmentManager: FragmentsManager,
    private var fbOps: FirebaseOps
) : MvpPresenter<AuthView>() {

    private var disposables = CompositeDisposable()

    private fun displayProfile(authResult: Boolean, fm: FragmentManager) {
        viewState.showProfile(authResult, fm)
        return
    }

    fun showFragment(fragment: Fragment, fm: FragmentManager) {
        fragmentManager.openFragment(fragment, fm)
        return
    }

    fun login(email: String, password: String, fragmentManager: FragmentManager) {
        getSignInSingle(email, password).subscribe({
            if (it != null) {
                displayProfile(true, fragmentManager)
            } else {
                displayProfile(false, fragmentManager)
            }
        }, {
            Log.e("afObserver", "subscription fail")
            it.stackTrace
        })
            .let { disposables.add(it) }
    }

    fun register(email: String, password: String, fragmentManager: FragmentManager) {
        getSignUpSingle(email, password).subscribe({
            if (it.user != null) {
                displayProfile(true, fragmentManager)
            } else {
                displayProfile(false, fragmentManager)
            }
        }, {
            Log.e("afObserver", "subscription fail")
            it.stackTrace
        })
            .let { disposables.add(it) }
    }

    private fun getSignInSingle(email: String, password: String): Single<AuthResult> {
        return fbOps.getLoginSingle(email, password)
    }

    private fun getSignUpSingle(email: String, password: String): Single<AuthResult> {
        return fbOps.getRegisterSingle(email, password)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}