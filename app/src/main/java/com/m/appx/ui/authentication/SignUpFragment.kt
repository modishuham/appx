package com.m.appx.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.m.appx.R
import com.m.appx.utils.AppUtils
import kotlinx.android.synthetic.main.fragment_signup.*

class SignUpFragment : Fragment() {

    private var isAllFieldsValid: Boolean = true
    private var emailPasswordAuthentication = EmailPasswordAuthentication()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_signup.setOnClickListener {
            if (validateFields()) {
                emailPasswordAuthentication.createUser(
                    et_email_signup.text.toString(),
                    et_password_signup.text.toString()
                )
            }
        }
        handelObserver()
    }

    private fun handelObserver() {
        emailPasswordAuthentication.loginSuccess.observe(viewLifecycleOwner, Observer {
            AppUtils.showToast(it)
        })
        emailPasswordAuthentication.loginFail.observe(viewLifecycleOwner, Observer {
            AppUtils.showToast(it)
        })
    }

    private fun validateFields(): Boolean {
        isAllFieldsValid = true
        if (et_email_signup.text!!.isEmpty() or
            !et_email_signup.text.toString().contains("@") or
            !et_email_signup.text.toString().contains(".com")
        ) {
            isAllFieldsValid = false
            til_email_signup.error = getString(R.string.error_email)
        } else {
            til_email_signup.error = ""
        }

        if (et_username_signup.text!!.isEmpty()) {
            isAllFieldsValid = false
            til_username_signup.error = getString(R.string.error_username)
        } else {
            til_username_signup.error = ""
        }

        if (et_password_signup.text!!.isEmpty() or
            et_password_signup.text.toString().contains(" ")
        ) {
            isAllFieldsValid = false
            til_password_signup.error = getString(R.string.error_password)
        } else {
            til_password_signup.error = ""
        }

        if (et_mobile_signup.text!!.isEmpty() or
            et_mobile_signup.text.toString().contains(" ")
        ) {
            isAllFieldsValid = false
            til_mobile_signup.error = getString(R.string.error_mobile)
        } else {
            til_mobile_signup.error = ""
        }
        return isAllFieldsValid
    }

}