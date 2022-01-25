package com.pseedk.trampampam.ui.fragments

import com.pseedk.trampampam.R
import com.pseedk.trampampam.utilits.APP_ACTIVITY

class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {
    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Контакты"
    }
}