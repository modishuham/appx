package com.m.appx.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.m.appx.R
import com.m.appx.ui.MainActivity
import com.m.appx.ui.authentication.AuthenticationActivity
import com.m.appx.ui.walkthrough.WalkThroughActivity
import com.m.appx.ui.walkthrough.WalkThroughAdapter

class SplashActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if (mAuth.currentUser != null) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, WalkThroughActivity::class.java))
            }
            finish()
        }, 3000)
    }

}
