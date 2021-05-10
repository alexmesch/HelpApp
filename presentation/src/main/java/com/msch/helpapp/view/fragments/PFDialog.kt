package com.msch.helpapp.view.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.msch.helpapp.R
import kotlinx.android.synthetic.main.fragment_profile_screen.*

class PFDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = it.layoutInflater

            builder.setView(inflater.inflate(R.layout.pf_dialog, pf_RecyclerView))
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
