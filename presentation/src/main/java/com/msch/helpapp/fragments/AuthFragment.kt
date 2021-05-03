package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.R
import com.msch.helpapp.data.EventDetailsRepo
import com.msch.helpapp.fragments.FragmentsManager.openFragment
import com.msch.helpapp.implementations.*
import com.msch.helpapp.interactors.*
import kotlinx.android.synthetic.main.fragment_auth_screen.view.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthFragment : Fragment() {
    @Inject
    lateinit var interactors: Interactors

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth_screen, container, false)
        val loginBtn = view.findViewById<Button>(R.id.login_btn)
        val registerBtn = view.findViewById<TextView>(R.id.signup)
        val signUpBtn = view.findViewById<Button>(R.id.register_btn)

        interactors = Interactors(
            AddEvents(EventDetailsRepo(EventDetailsDsImpl())),
            FilterNews(FilterNewsImpl()),
            AddCategories(CategoryItemsDsImpl()),
            GetUserInfo(GetUserInfoImpl()),
            GenerateSearchResults(SearchResultsGenerator()),
            FirebaseLogin(FirebaseLoginImpl()),
            FirebaseReg(FirebaseRegImpl())
        )

        val loginListener = OnClickListener {
            val email = view.findViewById<EditText>(R.id.email_field).text.toString()
            val password = view.findViewById<EditText>(R.id.password_field).text.toString()
            MainScope().launch {
                var isSuccessLogin: Boolean
                withContext(IO) {
                    isSuccessLogin = interactors.firebaseLogin(email, password)
                }
                if (isSuccessLogin) {
                    activity?.supportFragmentManager?.popBackStack()
                    openFragment(ProfileFragment(), requireFragmentManager())
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Не удалось выполнить вход",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                view.af_loadingScreen.visibility = GONE
            }
        }

        val regListener = OnClickListener {
            val email = view.findViewById<EditText>(R.id.email_field).text.toString()
            val password = view.findViewById<EditText>(R.id.password_field).text.toString()
            MainScope().launch {
                var isSuccessReg: Boolean
                withContext(IO) {
                    isSuccessReg = interactors.firebaseReg(email, password)
                }
                if (isSuccessReg) {
                    activity?.supportFragmentManager?.popBackStack()
                    openFragment(ProfileFragment(), requireFragmentManager())
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Не удалось зарегистрироваться",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                view.af_loadingScreen.visibility = GONE
            }
        }

        loginBtn.setOnClickListener(loginListener)
        registerBtn.setOnClickListener { registerSwitch(view) }
        signUpBtn.setOnClickListener (regListener)
        return view
    }

    private fun registerSwitch(v: View) {
        v.login_btn.visibility = GONE
        v.register_btn.visibility = VISIBLE
        v.signup.visibility = INVISIBLE
    }
}
