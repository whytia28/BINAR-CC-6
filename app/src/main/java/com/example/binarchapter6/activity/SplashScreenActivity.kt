package com.example.binarchapter6.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.binarchapter6.R
import com.example.binarchapter6.welcome.WelcomeActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(
                Intent(
                    this@SplashScreenActivity,
                    WelcomeActivity::class.java
                )
            )
            finish()
        }, 3000)
    }
}