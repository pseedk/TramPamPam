package com.pseedk.trampampam.ui.fragments

import androidx.fragment.app.Fragment
import com.pseedk.trampampam.R
import com.pseedk.trampampam.utilits.APP_ACTIVITY

class ChatFragment : Fragment(R.layout.fragment_chat) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Чаты"
    }

}