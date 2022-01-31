package com.pseedk.trampampam.ui.fragments

import androidx.fragment.app.Fragment
import com.pseedk.trampampam.R
import com.pseedk.trampampam.utilits.APP_ACTIVITY
import com.pseedk.trampampam.utilits.hideKeyboard

class MainFragment : Fragment(R.layout.fragment_chat) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Telegram"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
    }

}