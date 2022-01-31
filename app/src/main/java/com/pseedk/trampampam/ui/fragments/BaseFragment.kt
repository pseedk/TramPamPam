package com.pseedk.trampampam.ui.fragments

import androidx.fragment.app.Fragment
import com.pseedk.trampampam.MainActivity
import com.pseedk.trampampam.utilits.APP_ACTIVITY


open class BaseFragment(layout: Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()
    }
}