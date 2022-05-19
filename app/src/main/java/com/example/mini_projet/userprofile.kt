package com.example.mini_projet

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import com.bumptech.glide.Glide
import com.example.mini_projet.nameuser
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_userprofile.*

lateinit var nameuser : TextInputEditText

class userprofile : AppCompatActivity() {
    private lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userprofile)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        nameuser.setText(mSharedPref.getString(NAME, "").toString())
        Log.e("username = = : ", mSharedPref.getString(USERNAME,"").toString())

            val filename2 = mSharedPref.getString(AVATAR, "").toString()
            val path = "https://firebasestorage.googleapis.com/v0/b/mini-projet-2e934.appspot.com/o/images%2F"+filename2+"?alt=media"
            Glide.with(this)
                .load(path)
                .into(logoPic)

    }
}