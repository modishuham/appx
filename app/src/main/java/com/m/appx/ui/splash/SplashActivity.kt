package com.m.appx.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.m.appx.R
import com.m.appx.ui.MainActivity
import com.m.appx.ui.authentication.AuthenticationActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            Runnable {
                startActivity(Intent(this, AuthenticationActivity::class.java))
                finish()
            },
            3000
        )
    }

}
