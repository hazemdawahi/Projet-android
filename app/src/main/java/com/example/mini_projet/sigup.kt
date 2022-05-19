package com.example.mini_projet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.mini_projet.models.User
import com.example.mini_projet.utils.ApiInterface
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class sigup : AppCompatActivity() {
    lateinit var pwdid: TextInputEditText
    lateinit var Languageid: TextInputEditText
    lateinit var lvlid: TextInputEditText
    lateinit var Nameid: TextInputEditText
    lateinit var emailid: TextInputEditText
    lateinit var confirmpwdid: TextInputEditText

    lateinit var txtLayoutPass: TextInputLayout
    lateinit var txtLayoutLanguage: TextInputLayout
    lateinit var txtLayoutName: TextInputLayout
    lateinit var txtLayoutLvl: TextInputLayout
    lateinit var txtLayoutConfirmPwd: TextInputLayout
    lateinit var txtLayoutEmail: TextInputLayout


    lateinit var btnsignup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigup)
        pwdid = findViewById(R.id.pwdid)
        Languageid =findViewById(R.id.Languageid)
        lvlid=findViewById(R.id.lvlid)
        Nameid=findViewById(R.id.Nameid)
        emailid = findViewById(R.id.emailid)
        confirmpwdid = findViewById(R.id.confirmpwdid)
 btnsignup = findViewById(R.id.btnsignup)
        txtLayoutPass = findViewById(R.id.txtLayoutPass)
        txtLayoutLanguage = findViewById(R.id.txtLayoutLanguage)
        txtLayoutName = findViewById(R.id.txtLayoutName)
        txtLayoutLvl = findViewById(R.id.txtLayoutLvl)
        txtLayoutConfirmPwd = findViewById(R.id.txtLayoutConfirmPwd)
        txtLayoutEmail = findViewById(R.id.txtLayoutEmail)

        btnsignup.setOnClickListener {
            Register()
        }
    }
    private fun Register(){
        if (validate()){
            val apiInterface = ApiInterface.create()
            val map: HashMap<String, String> = HashMap()

            map["username"] = Nameid.text.toString()
            map["level"] = lvlid.text.toString()
            map["email"] = emailid.text.toString()
            map["password"] = pwdid.text.toString()
            map["confirmPwd"] = confirmpwdid.text.toString()
            map["language"] = Languageid.text.toString()
            apiInterface.signup(map).enqueue(object : Callback<User> {

                override fun onResponse(call: Call<User>, response: Response<User>) {

                    val user = response.body()
                    if (user != null){
                        val intent = Intent(this@sigup, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@sigup, "Connexion error!", Toast.LENGTH_SHORT).show()
                }

            })

        }
    }
    private fun validate(): Boolean {


        Nameid.error = null
        pwdid.error = null
        Languageid.error = null
        lvlid.error = null
        emailid.error = null
        confirmpwdid.error = null

        if (Nameid.text!!.isEmpty()){
            Nameid.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (Nameid.text!!.length < 4 ){
            Nameid.error = getString(R.string.Usernameslength)
            return false
        }
        if (pwdid.text!!.isEmpty()){
            pwdid.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (Languageid.text!!.isEmpty()){
            Languageid.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (lvlid.text!!.isEmpty()){
            lvlid.error = getString(R.string.level)
            return false
        }
        if (emailid.text!!.isEmpty()){
            emailid.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (confirmpwdid.text!!.isEmpty()){
            confirmpwdid.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        return true
    }



}