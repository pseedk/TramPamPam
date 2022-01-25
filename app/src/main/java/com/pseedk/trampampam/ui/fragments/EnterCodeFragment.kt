package com.pseedk.trampampam.ui.fragments

import androidx.fragment.app.Fragment
import com.google.firebase.auth.PhoneAuthProvider
import com.pseedk.trampampam.MainActivity
import com.pseedk.trampampam.R
import com.pseedk.trampampam.activities.RegisterActivity
import com.pseedk.trampampam.utilits.*
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment(val mPhoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {


    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = mPhoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher {
            val string = register_input_code.text.toString()
            if (string.length == 6) {
                enterCode()
            }

        })
    }

    private fun enterCode() {
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = mPhoneNumber
                dateMap[CHILD_USERNAME] = uid

                REF_DATABASE_ROOT.child(NODE_PHONES).child(mPhoneNumber).setValue(uid)
                    .addOnFailureListener {showToast(it.message.toString())  }
                    .addOnSuccessListener {
                        REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                            .addOnSuccessListener {
                                showToast("Добро пожаловать")
                                (activity as RegisterActivity).replaceActivity(MainActivity())
                            }
                            .addOnFailureListener { showToast(it.message.toString())  }
                    }

            } else showToast(task.exception?.message.toString())
        }
    }
}