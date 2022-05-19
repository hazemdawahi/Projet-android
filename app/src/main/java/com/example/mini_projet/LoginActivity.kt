package com.example.mini_projet

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.util.Log
import android.widget.Toast
import com.example.mini_projet.models.User
import com.example.mini_projet.utils.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
const val PREF_NAME = "DATA_PREF"
const val IS_REMEMBRED = "IS_REMEMBRED"

private lateinit var login: Button
private lateinit var reset : Button
private lateinit var signup : Button
const val USERNAME = "USERNAME"
const val PASSWORD = "PASSWORD"
const val EMAIL = "EMAIL"
const val ID = "ID"
const val IDE = "IDE"
const val AVATAR = "No Picture"
const val SCORE = "0"
const val VIE = "10"
const val NAME = "NAME"
class LoginActivity : AppCompatActivity() {
    lateinit var usernmane: TextInputEditText
    lateinit var  txtLayoutLogin : TextInputLayout

    lateinit var pwd : TextInputEditText
    lateinit var txtLayoutPassword : TextInputLayout
    private lateinit var mSharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        usernmane = findViewById(R.id.emailid)
        txtLayoutLogin = findViewById(R.id.txtLayoutLogin)
        pwd = findViewById(R.id.txtPassword)
        txtLayoutPassword = findViewById(R.id.txtLayoutPassword)
        login = findViewById(R.id.btnLogin3)
        signup = findViewById(R.id.btnsignup)
        reset = findViewById(R.id.btnfpwd)
        login.setOnClickListener{
            val addIntent: Intent = Intent(this, MainActivity::class.java)
            doLogin()

        }
        /*
        reset.setOnClickListener{
            val addIntent: Intent = Intent(this,forgetpsd::class.java)
            startActivity(addIntent)
        }

         */
        signup.setOnClickListener{
            val addIntent: Intent = Intent(this,sigup::class.java)
            startActivity(addIntent)
        }

        reset.setOnClickListener{
            val addIntent: Intent = Intent(this,forgetpsd::class.java)
            startActivity(addIntent)
        }
        /*
        login.setOnClickListener {
            doLogin()
        }
        signup.setOnClickListener{
            val intent = Intent(this, sigup::class.java)
            startActivity(intent)
        }
       */
    }

    private fun doLogin(){
        if (validate()){

            val apiInterface = ApiInterface.create()
            val map: HashMap<String, String> = HashMap()

            map["email"] = usernmane.text.toString()
            map["password"] = pwd.text.toString()
            CoroutineScope(Dispatchers.IO).launch {

                apiInterface.login(map).enqueue(object : Callback<User>{

                    override fun onResponse(call: Call<User>, response:
                    Response<User>
                    ) {
                        val user = response.body() as User
                        Log.d("myTag", "ghghh");

                        Log.e("user : ", user.toString())
                        if((user!!.password != null)||(user!!.email!=null)||(user!!.password!=null))
                        {
                            Log.e("user after test : ", user.toString())
                            Log.e("user id : ", user._id.toString())
                            mSharedPref.edit().apply{
                                putString(ID, user._id.toString())
                                putString(USERNAME, user.email)
                                putString(NAME, user.name)
                                putString(IDE, user._id.toString())
                                putString(PASSWORD, pwd.text.toString())
                                putString(SCORE,user.scores[0].toString())
                                putString(VIE,user.vie.toString())
                                putBoolean(IS_REMEMBRED, true)
                            }.apply()
                            Log.e("id user",mSharedPref.getString(ID, "").toString())

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }

                        else
                        {
                            Toast.makeText(this@LoginActivity,"Username or Password wrong !!",Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {

                        Toast.makeText(this@LoginActivity,"Connexion error!",Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
    private fun validate(): Boolean {
        txtLayoutLogin.error = null
        txtLayoutPassword.error = null

        if (usernmane.text!!.isEmpty()){
            txtLayoutLogin.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        if (pwd.text!!.isEmpty()){
            txtLayoutPassword.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        return true
    }

}