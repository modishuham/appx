package com.m.appx.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.m.appx.R
import com.m.appx.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
        }

        FirebaseAuth.getInstance().currentUser?.let {
            tv_username_account.text = it.displayName
            tv_mobile_account.text = it.phoneNumber
            tv_email_account.text = it.email
        }

        btn_goto_service_profile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_serviceProfileFragment)
        }

    }
}