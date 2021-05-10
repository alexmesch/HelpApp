package com.msch.helpapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.msch.helpapp.DI.DependencyInjectorImpl
import com.msch.helpapp.R
import com.msch.helpapp.view.fragments.FragmentsManager.openFragment
import com.msch.helpapp.presenter.AuthPresenter
import com.msch.helpapp.view.contracts.AuthContract
import kotlinx.android.synthetic.main.fragment_auth_screen.view.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthFragment : Fragment(), AuthContract.AuthView{
    private lateinit var presenter: AuthContract.AuthPresenter
    private val authScope = MainScope()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth_screen, container, false)
        setPresenter(AuthPresenter(this, DependencyInjectorImpl()))

        val loginListener = OnClickListener {
            var authResult: Boolean
            authScope.launch {
                val email = view.findViewById<EditText>(R.id.email_field).text.toString()
                val password = view.findViewById<EditText>(R.id.password_field).text.toString()
                withContext(IO) {
                    authResult = presenter.performLogin(email, password)
                    Log.d("e&p", "$email $password")
                }
                displayProfile(authResult)
            }
        }

        val regListener = OnClickListener {
            var authResult: Boolean
            authScope.launch {
                val email = view.findViewById<EditText>(R.id.email_field).text.toString()
                val password = view.findViewById<EditText>(R.id.password_field).text.toString()
                withContext(IO) {
                    authResult = presenter.performReg(email, password)
                }
                displayProfile(authResult)
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

    override fun setPresenter(presenter: AuthContract.AuthPresenter) {
        this.presenter = presenter
    }

    override fun displayProfile(authResult: Boolean) {
       if (authResult) {
           activity?.supportFragmentManager?.popBackStack()
           openFragment(ProfileFragment(), requireFragmentManager())
       }
       else {
            Toast.makeText(
                requireContext(),
                "Не удалось выполнить вход",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
