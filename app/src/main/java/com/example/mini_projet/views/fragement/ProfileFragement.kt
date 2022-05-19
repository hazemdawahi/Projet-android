package com.example.mini_projet.views.fragement

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mini_projet.*
import com.example.mini_projet.R
import kotlinx.android.synthetic.main.activity_userprofile.*



class ProfileFragement : Fragment(R.layout.activity_userprofile) {
    private lateinit var mSharedPref: SharedPreferences

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        var rootView: View = inflater.inflate(R.layout.activity_userprofile, container, false)

        return rootView
    }

    fun refresh(context: Context?)
    {
        context?.let {
            val fragementManager = (context as? AppCompatActivity)?.supportFragmentManager
            fragementManager?.let {
                val currentFragement = fragementManager.findFragmentById(R.id.container)
                currentFragement?.let {
                    val fragmentTransaction = fragementManager.beginTransaction()
                    fragmentTransaction.detach(it)
                    fragmentTransaction.attach(it)
                    fragmentTransaction.commit()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        mSharedPref = requireContext().getSharedPreferences(PREF_NAME, AppCompatActivity.MODE_PRIVATE);
      nameuser.setText(mSharedPref.getString(USERNAME, "").toString())

        Log.e("username = = : ", mSharedPref.getString(USERNAME,"").toString())


        if(mSharedPref.getString(AVATAR, "").toString()=="No Picture")
        {
            logoPic.setImageResource(R.drawable.avatar)
        }
        else
        {
            val filename2 = mSharedPref.getString(AVATAR, "").toString()
            val path = "https://firebasestorage.googleapis.com/v0/b/mini-projet-2e934.appspot.com/o/images%2F"+filename2+"?alt=media"
            Glide.with(context)
                .load(path)
                .into(logoPic)
        }

        modifierprofil.setOnClickListener {
        val intent = Intent(context,EditProfile::class.java)
        startActivity(intent)
}
        Payment.setOnClickListener {
            val intent = Intent(context,Paypal::class.java)
            startActivity(intent)
        }

}

}