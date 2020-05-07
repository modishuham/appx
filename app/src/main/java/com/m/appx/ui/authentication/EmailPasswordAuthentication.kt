package com.m.appx.ui.authentication

import SingleLiveEvent
import com.google.firebase.auth.FirebaseAuth

class EmailPasswordAuthentication {

    private val user: FirebaseAuth = FirebaseAuth.getInstance()
    var loginSuccess: SingleLiveEvent<String> = SingleLiveEvent()
    var loginFail: SingleLiveEvent<String> = SingleLiveEvent()

    fun createUser(email: String, password: String) {
        user.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                loginSuccess.value = "success"
            } else {
                loginFail.value = it.exception?.message
            }
        }
    }

    fun loginUser(email: String, password: String) {
        user.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful)
                loginSuccess.value = "SignUp successfully"
            else
                loginFail.value = it.exception?.message
        }
    }

}