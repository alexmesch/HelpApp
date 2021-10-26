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
import com.msch.helpapp.dagger.components.ActivityComponent
import com.msch.helpapp.dagger.components.DaggerFragmentComponent
import com.msch.helpapp.presenters.AuthPresenter
import com.msch.helpapp.views.AuthView
import kotlinx.android.synthetic.main.fragment_auth_screen.view.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class AuthFragment : BaseFragment(), AuthView {

    @field:InjectPresenter
    @get:ProvidePresenter
    @Inject
    lateinit var authPresenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        if (!::authPresenter.isInitialized)
            DaggerFragmentComponent.builder()
                .activityComponent(this.getActivityComponent(ActivityComponent::class.java))
                .build()
                .inject(this)
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
            authPresenter.login(email, password, fm)
            view.af_loadingScreen.visibility = GONE
        }

        val regListener = OnClickListener {
            val email = view.findViewById<EditText>(R.id.email_field).text.toString()
            val password = view.findViewById<EditText>(R.id.password_field).text.toString()
            authPresenter.register(email, password, fm)
            view.af_loadingScreen.visibility = GONE
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
            authPresenter.showFragment(ProfileFragment())
        } else {
            Toast.makeText(
                activity?.applicationContext,
                "Не удалось выполнить вход",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
