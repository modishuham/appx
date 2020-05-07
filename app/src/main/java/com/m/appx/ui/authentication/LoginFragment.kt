package com.m.appx.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.m.appx.R
import com.m.appx.ui.MainActivity
import com.m.appx.utils.AppUtils
import kotlinx.android.synthetic.main.fragment_login.*
import org.json.JSONObject
import java.util.*

class LoginFragment : Fragment(), View.OnClickListener {

    private val RC_SIGN_IN: Int = 123

    //For google login
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuth: FirebaseAuth

    // For facebook
    private lateinit var callbackManager: CallbackManager
    private var isAllFieldsValid: Boolean = true
    private var emailPasswordAuthentication = EmailPasswordAuthentication()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val accessToken = AccessToken.getCurrentAccessToken()
        callbackManager = CallbackManager.Factory.create()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(context!!, gso)
        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            startActivity(Intent(context, MainActivity::class.java))
            activity!!.finish()
        }

        tv_go_signup.setOnClickListener(this)
        btn_login.setOnClickListener(this)
        btn_google_login.setOnClickListener(this)
        setUpFacebookLogin()
        handleObserver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun handleObserver() {
        emailPasswordAuthentication.loginSuccess.observe(viewLifecycleOwner, Observer {
            startActivity(Intent(context, MainActivity::class.java))
        })
        emailPasswordAuthentication.loginFail.observe(viewLifecycleOwner, Observer {
            AppUtils.showToast(it)
        })
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("Login", "firebaseAuthWithGoogle:" + acct.id!!)
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    startActivity(Intent(context, MainActivity::class.java))
                }
            }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                if (validateFields())
                    emailPasswordAuthentication.loginUser(
                        et_email_login.text.toString(),
                        et_password_login.text.toString()
                    )
            }
            R.id.btn_google_login -> {
                signIn()
            }
            R.id.tv_go_signup -> {
                findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
            }
        }
    }

    private fun setUpFacebookLogin() {

        btn_facebook_login.setReadPermissions(listOf("email", "public_profile"))
        // If you are using in a fragment, call loginButton.setFragment(this);
        btn_facebook_login.fragment = this

        // Callback registration
        btn_facebook_login.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    Log.e(
                        "facebook",
                        "success" + " " + result?.accessToken + " " + result?.accessToken?.token
                    )
                    handleFacebookAccessToken(result?.accessToken?.token)
                }

                override fun onCancel() {
                    Log.e("facebook", "cancel")
                }

                override fun onError(error: FacebookException?) {
                    Log.e("facebook", "error")
                }
            })
    }

    private fun handleFacebookAccessToken(token: String?) {
        token?.let {
            val credential = FacebookAuthProvider.getCredential(token)
            mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    Log.e(
                        "Info",
                        "" + user?.displayName + " " + user?.email + " " + user?.phoneNumber + " " + user?.photoUrl
                    )
                    startActivity(Intent(context, MainActivity::class.java))
                } else {

                }
            }.addOnFailureListener {
                Log.e("facebook", it.toString())
            }
        }
    }


    private fun validateFields(): Boolean {
        isAllFieldsValid = true
        if (et_email_login.text!!.isEmpty() or
            !et_email_login.text.toString().contains("@") or
            !et_email_login.text.toString().contains(".com")
        ) {
            isAllFieldsValid = false
            til_email_login.error = getString(R.string.error_email)
        } else {
            til_email_login.error = ""
        }

        if (et_password_login.text!!.isEmpty() or
            et_password_login.text.toString().contains(" ")
        ) {
            isAllFieldsValid = false
            til_password_login.error = getString(R.string.error_password)
        } else {
            til_password_login.error = ""
        }
        return isAllFieldsValid
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
            }
        }
    }
}