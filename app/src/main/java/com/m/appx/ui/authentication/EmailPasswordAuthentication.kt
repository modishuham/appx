package com.m.appx.ui.authentication

import SingleLiveEvent
import com.google.firebase.auth.FirebaseAuth
import com.m.appx.storage.FirebaseDatabaseStorage

class EmailPasswordAuthentication {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var loginSuccess: SingleLiveEvent<String> = SingleLiveEvent()
    var loginFail: SingleLiveEvent<String> = SingleLiveEvent()

    fun createUser(user: User) {
        mAuth.createUserWithEmailAndPassword(user.email!!, user.password!!).addOnCompleteListener {
            if (it.isSuccessful) {
                val isStored = FirebaseDatabaseStorage.saveSignUpData(user)
                if (isStored)
                    loginSuccess.value = "success"
                else
                    loginFail.value = "Something went wrong"
            } else {
                loginFail.value = it.exception?.message
            }
        }
    }

    fun loginUser(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful)
                loginSuccess.value = "SignUp successfully"
            else
                loginFail.value = it.exception?.message
        }
    }

}