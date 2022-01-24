package com.pseedk.trampampam.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pseedk.trampampam.R
import com.pseedk.trampampam.databinding.ActivityRegisterBinding
import com.pseedk.trampampam.ui.fragments.EnterPhoneNumberFragment
import com.pseedk.trampampam.utilits.initFirebase
import com.pseedk.trampampam.utilits.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFirebase()
    }

    override fun onStart() {
        super.onStart()
        mToolbar = mBinding.registerToolbar
        setSupportActionBar(mToolbar)
        title = getString(R.string.register_title_your_phone)
        replaceFragment(EnterPhoneNumberFragment(), false)
    }
}