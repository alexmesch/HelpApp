package com.msch.helpapp.fragments

import android.os.Bundle
import android.util.Log
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.R
import com.msch.helpapp.fragments.FragmentsManager.openFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_auth_screen.view.*

class AuthFragment: Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth_screen, container, false)
        val loginBtn = view.findViewById<Button>(R.id.login_btn)
        val registerBtn = view.findViewById<TextView>(R.id.signup)
        val signUpBtn = view.findViewById<Button>(R.id.register_btn)

        loginBtn.setOnClickListener { login(view) }
        registerBtn.setOnClickListener { registerSwitch(view) }
        signUpBtn.setOnClickListener { signUp(view) }
        return view
    }

    private fun login(v: View) {
        val emailField = v.findViewById<EditText>(R.id.email_field)
        val pswrdField = v.findViewById<EditText>(R.id.password_field)

        val email = emailField.text.toString()
        val password = pswrdField.text.toString()
        auth = Firebase.auth

        if(email != "" && password != "" ) {
            v.af_loadingScreen.visibility = VISIBLE
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        Log.d("Firebase", "signInWithEmail: success")
                        auth.currentUser
                        activity?.supportFragmentManager?.popBackStack()
                        openFragment(ProfileFragment(), this.requireFragmentManager())
                    } else {
                        Log.w("Firebase", "signInWithEmail: failure", task.exception)
                        Toast.makeText(
                            requireContext(),
                            "Не удалось выполнить вход",
                            Toast.LENGTH_SHORT
                        ).show()
                        v.af_loadingScreen.visibility = GONE
                    }
                }
        }
        else {
            Toast.makeText(
                requireContext(),
                "Одно или несколько полей пусты",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun registerSwitch(v: View) {
        v.login_btn.visibility = GONE
        v.register_btn.visibility = VISIBLE
        v.signup.visibility = INVISIBLE
    }

    private fun signUp(v: View) {
        val email = v.email_field.text.toString()
        val password = v.password_field.text.toString()
        auth = Firebase.auth

        if(email != "" && password != "" ) {
            v.af_loadingScreen.visibility = VISIBLE
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        Log.d("signUp", "createUserWithEmail: success")
                        auth.currentUser
                        activity?.supportFragmentManager?.popBackStack()
                        openFragment(ProfileFragment(), requireFragmentManager())
                    } else {
                        Log.w("signUp", "createUserWithEmail: failure", task.exception)
                        Toast.makeText(
                            requireContext(),
                            "Не удалось зарегистрироваться",
                            Toast.LENGTH_SHORT
                        ).show()
                        v.af_loadingScreen.visibility = GONE
                    }
                }
        }
        else {
            Toast.makeText(
                requireContext(),
                "Одно или несколько полей пусты",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}