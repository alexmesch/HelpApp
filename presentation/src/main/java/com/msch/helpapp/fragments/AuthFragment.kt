package com.msch.helpapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.AuthResult
import com.msch.helpapp.R
import com.msch.helpapp.dagger.components.DaggerFirebaseComponent
import com.msch.helpapp.dagger.modules.FirebaseModule
import com.msch.helpapp.dagger.modules.FragmentManagerModule
import com.msch.helpapp.presenters.AuthPresenter
import com.msch.helpapp.views.AuthView
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_auth_screen.view.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class AuthFragment : MvpAppCompatFragment(), AuthView {
    private val disposables = CompositeDisposable()

    @field:InjectPresenter
    @get:ProvidePresenter
    @Inject
    lateinit var authPresenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        if (!::authPresenter.isInitialized) {
            DaggerFirebaseComponent
                .builder()
                .firebaseModule(FirebaseModule())
                .fragmentManagerModule(FragmentManagerModule())
                .build()
                .inject(this)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth_screen, container, false)
        val fm = requireActivity().supportFragmentManager

        val loginListener = OnClickListener {
            val email = view.findViewById<EditText>(R.id.email_field).text.toString()
            val password = view.findViewById<EditText>(R.id.password_field).text.toString()
            authPresenter.getSignInObservable(email, password)
                .subscribe(object : SingleObserver<AuthResult> {
                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d)
                    }

                    override fun onSuccess(t: AuthResult) {
                        if (t.user != null) {
                            authPresenter.displayProfile(true, fm)
                        } else {
                            authPresenter.displayProfile(false, fm)
                        }
                        disposables.clear()
                        view.af_loadingScreen.visibility = GONE
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(
                            requireContext(),
                            "Неверный логин или пароль",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("afObserver", "subscription fail")
                        e.stackTrace
                    }

                })
        }

        val regListener = OnClickListener {
            val email = view.findViewById<EditText>(R.id.email_field).text.toString()
            val password = view.findViewById<EditText>(R.id.password_field).text.toString()
            authPresenter.getSignUpObservable(email, password)
                .subscribe(object : SingleObserver<AuthResult> {
                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d)
                    }

                    override fun onSuccess(t: AuthResult) {
                        if (t.user != null) {
                            authPresenter.displayProfile(true, fm)
                        } else {
                            authPresenter.displayProfile(false, fm)
                        }
                        disposables.clear()
                        view.af_loadingScreen.visibility = GONE
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(
                            requireContext(),
                            "Не удалось зарегистрироваться",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("afObserver", "subscription fail")
                        e.stackTrace
                    }

                })
        }

        view.login_btn.setOnClickListener(loginListener)
        view.register_btn.setOnClickListener(regListener)
        view.signup.setOnClickListener { registerSwitch(view) }
        return view
    }

    private fun registerSwitch(v: View) {
        v.login_btn.visibility = GONE
        v.register_btn.visibility = VISIBLE
        v.signup.visibility = INVISIBLE
    }

    override fun showProfile(authResult: Boolean, fm: FragmentManager) {
        if (authResult) {
            fm.popBackStack()
            authPresenter.showFragment(ProfileFragment(), fm)
        } else {
            Toast.makeText(
                activity?.applicationContext,
                "Не удалось выполнить вход",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
