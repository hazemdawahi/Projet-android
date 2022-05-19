package com.example.mini_projet

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.mini_projet.views.fragement.MainFragement
import com.example.mini_projet.views.fragement.ProfileFragement
import com.example.mini_projet.views.fragement.RtcFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*





class MainActivity : AppCompatActivity() {
    private lateinit var lvl1b : ImageButton

    private lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lvl1b = findViewById(R.id.lvl1b)
        logoPic3.setBackgroundResource(R.drawable.ic_2g);
        logoPic.setBackgroundResource(R.drawable.ic_3g);
        logoPic5.setBackgroundResource(R.drawable.ic_4g);
        logoPic4.setBackgroundResource(R.drawable.ic_5g);
        logoPic7.setBackgroundResource(R.drawable.ic_6g);
        logoPic6.setBackgroundResource(R.drawable.ic_7g);
        logoPic8.setBackgroundResource(R.drawable.ic_8g);

        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        textView2.setText(mSharedPref.getString(VIE, "").toString())

        lvl1b.setOnClickListener {
            val addIntent: Intent = Intent(this, lvl1::class.java)
            startActivity(addIntent)
        }
        var score = mSharedPref.getString(SCORE, "")
        if (score != null) {
            if (score == "225") {
                logoPic3.setBackgroundResource(R.drawable.lvl2);
                logoPic3.setOnClickListener {
                    val addIntent: Intent = Intent(this, lvl2::class.java)
                    startActivity(addIntent)
                }
            }
            if (score == "450") {
                logoPic.setBackgroundResource(R.drawable.lvl333);
                logoPic.setOnClickListener {
                    val addIntent: Intent = Intent(this, lvl3::class.java)
                    startActivity(addIntent)
                }
            }
            if (score == "675") {
                logoPic5.setBackgroundResource(R.drawable.lvl444);
                logoPic5.setOnClickListener {
                    val addIntent: Intent = Intent(this, lvl4::class.java)
                    startActivity(addIntent)
                }
            }
            if (score == "900") {
                logoPic4.setBackgroundResource(R.drawable.lvl555);
                logoPic4.setOnClickListener {
                    val addIntent: Intent = Intent(this, lvl5::class.java)
                    startActivity(addIntent)
                }
            }
            if (score == "1125") {
                logoPic7.setBackgroundResource(R.drawable.lvl666);
                logoPic7.setOnClickListener {
                    val addIntent: Intent = Intent(this, lvl6::class.java)
                    startActivity(addIntent)
                }
            }
            if (score == "1350") {
                logoPic6.setBackgroundResource(R.drawable.lvl777);
                logoPic6.setOnClickListener {
                    val addIntent: Intent = Intent(this, lvl7::class.java)
                    startActivity(addIntent)
                }
            }
            if (score == "1575") {
                logoPic8.setBackgroundResource(R.drawable.lvl888);
                logoPic8.setOnClickListener {
                    val addIntent: Intent = Intent(this, lvl8::class.java)
                    startActivity(addIntent)
                }
            }
        }
        val homeFragment = MainFragement()
        val profileFragment = ProfileFragement()
        val rtcFragment = RtcFragment()
        makeCurrentFragment(homeFragment)

        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> {
                    // Respond to navigation item 1 click
                    makeCurrentFragment(homeFragment)
                    true
                }
                R.id.page_2 -> {
                    makeCurrentFragment(profileFragment)
                    // Respond to navigation item 2 click
                    true
                }
                R.id.page_3 -> {
                    makeCurrentFragment(rtcFragment)
                    // Respond to navigation item 3 click
                    true
                }
                else -> false
            }

        }



    }
    private fun makeCurrentFragment(fragment: Fragment)= supportFragmentManager.beginTransaction().apply{
        replace(R.id.container,fragment)
        commit()
    }
        }

