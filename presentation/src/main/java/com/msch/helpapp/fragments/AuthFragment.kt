package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.msch.helpapp.R
import com.msch.helpapp.presenters.AuthPresenter
import com.msch.helpapp.views.AuthView
import kotlinx.android.synthetic.main.fragment_auth_screen.view.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class AuthFragment : MvpAppCompatFragment(), AuthView {
    private val authScope = MainScope()

    @InjectPresenter(presenterId = "authPresenter")
    lateinit var authPresenter: AuthPresenter

    @ProvidePresenter
    fun providePresenter(): AuthPresenter {
        return AuthPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth_screen, container, false)
        val fm = requireActivity().supportFragmentManager
        providePresenter()

        val loginListener = OnClickListener {
            var authResult = false
            authScope.launch {
                val email = view.findViewById<EditText>(R.id.email_field).text.toString()
                val password = view.findViewById<EditText>(R.id.password_field).text.toString()
                withContext(IO) {
                    authResult = authPresenter.performLogin(email, password)
                }
                authPresenter.displayProfile(authResult, fm)
            }
        }

        val regListener = OnClickListener {
            var authResult = false
            authScope.launch {
                val email = view.findViewById<EditText>(R.id.email_field).text.toString()
                val password = view.findViewById<EditText>(R.id.password_field).text.toString()
                withContext(IO) {
                    authResult = authPresenter.performReg(email, password)
                }
                authPresenter.displayProfile(authResult, fm)
            }
        }

        view.login_btn.setOnClickListener(loginListener)
        view.register_btn.setOnClickListener(regListener)
        view.signup.setOnClickListener {registerSwitch(view)}
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
       }
       else {
            Toast.makeText(
                activity?.applicationContext,
                "Не удалось выполнить вход",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
