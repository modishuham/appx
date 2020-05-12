package com.m.appx.storage

import com.google.firebase.database.FirebaseDatabase
import com.m.appx.ui.authentication.User

object FirebaseDatabaseStorage {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun saveSignUpData(user: User): Boolean {
        var isSuccess: Boolean = false
        val databaseReference = firebaseDatabase.getReference("users")
        databaseReference.child("" + user.mobile).setValue(user).addOnSuccessListener {
            isSuccess = true
        }.addOnFailureListener {
            isSuccess = false
        }
        return isSuccess
    }
}