package com.pseedk.trampampam.ui.fragments

import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.pseedk.trampampam.MainActivity
import com.pseedk.trampampam.R
import com.pseedk.trampampam.activities.RegisterActivity
import com.pseedk.trampampam.utilits.AUTH
import com.pseedk.trampampam.utilits.replaceActivity
import com.pseedk.trampampam.utilits.replaceFragment
import com.pseedk.trampampam.utilits.showToast
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import java.util.concurrent.TimeUnit


class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {

    private lateinit var mPhoneNumber: String
    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onStart() {
        super.onStart()
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast("Добро пожаловать")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else showToast(it.exception?.message.toString())
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCodeFragment(mPhoneNumber, id))
            }

        }
        register_btn_next.setOnClickListener { sendCode() }
    }

    private fun sendCode() {

        if (register_input_phone_number.text.toString().isEmpty()) {
            showToast(getString(R.string.register_toast_enter_phone))
        } else {
            authUser()
        }
    }

    private fun authUser() {
        mPhoneNumber = register_input_phone_number.text.toString()
        PhoneAuthProvider.verifyPhoneNumber(
            PhoneAuthOptions
                .newBuilder(FirebaseAuth.getInstance())
                .setActivity(activity as RegisterActivity)
                .setPhoneNumber(mPhoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(mCallback)
                .build()
        )
    }
}