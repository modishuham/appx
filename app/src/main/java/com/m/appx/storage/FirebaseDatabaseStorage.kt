package com.m.appx.storage

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.m.appx.ui.authentication.User

object FirebaseDatabaseStorage {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun saveSignUpData(user: User) {
        val databaseReference = firebaseDatabase.getReference("users")
        databaseReference.child("" + user.mobile).setValue(user)
    }
}