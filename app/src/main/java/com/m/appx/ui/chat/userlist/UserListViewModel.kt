package com.m.appx.ui.chat.userlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.m.appx.ui.authentication.User

class UserListViewModel(application: Application) : AndroidViewModel(application) {

    private var mlUserList: MutableLiveData<ArrayList<User>> = MutableLiveData()
    private var users: ArrayList<User> = ArrayList()

    fun getUserList(): LiveData<ArrayList<User>> {
        FirebaseDatabase.getInstance().getReference("users")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(dbError: DatabaseError) {

                }

                override fun onDataChange(dbSnapShot: DataSnapshot) {
                    for (userSnapshot in dbSnapShot.children) {
                        users.add(
                            User(
                                userSnapshot.child("username").value.toString(),
                                userSnapshot.child("email").value.toString(),
                                userSnapshot.child("profileImage").value.toString(),
                                userSnapshot.child("password").value.toString(),
                                userSnapshot.child("mobile").value.toString().toLong()
                            )
                        )
                    }
                    mlUserList.value = users
                }
            })
        return mlUserList
    }
}