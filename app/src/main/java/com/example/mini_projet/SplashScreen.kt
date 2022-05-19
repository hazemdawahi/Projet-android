package com.example.mini_projet

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {
    private lateinit var mSharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this, LoginActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 3000)
        if (mSharedPref.getBoolean(IS_REMEMBRED, false)) {
            val homeIntent = Intent(this, MainActivity::class.java)

            startActivity(homeIntent)
            finish()

        } else {
            val homeIntent = Intent(this, LoginActivity::class.java)

            startActivity(homeIntent)
            finish()
        }
    }
    }
