package com.example.mini_projet

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mini_projet.models.Level
import com.example.mini_projet.utils.ApiInterface
import kotlinx.android.synthetic.main.activity_lvl2.*
import kotlinx.android.synthetic.main.activity_lvl2.btnsignup2
import kotlinx.android.synthetic.main.activity_lvl2.emailid3
import kotlinx.android.synthetic.main.activity_lvl2.question
import kotlinx.android.synthetic.main.activity_lvl2.textView
import kotlinx.android.synthetic.main.activity_lvl2.vieL
import kotlinx.android.synthetic.main.activity_lvl3.*
import kotlinx.android.synthetic.main.activity_lvl4.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class lvl4 : AppCompatActivity() {
    private var prog = 0
    lateinit var answer : String
    private lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_lvl4)
            mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
            Log.e("score",mSharedPref.getString(SCORE, "").toString())
            textView.setText("Score : "+mSharedPref.getString(SCORE, "").toString() )
            vieL.setText(mSharedPref.getString(VIE, "").toString())
            //  mSharedPref.getString(AVATAR, "").toString()
            lvl4m()
            btnsignup2.setOnClickListener {
                if(answer == emailid3.text.toString())
                {
                    var score = mSharedPref.getString(SCORE, "")?.toInt()?.plus(25)
                    textView.setText("Score : "+score )

                    mSharedPref.edit().apply{
                        putString(SCORE,score.toString())
                    }.apply()
                    Toast.makeText(this@lvl4,"Good", Toast.LENGTH_SHORT).show()
                }
                else
                {

                    var score = mSharedPref.getString(SCORE, "")
                    textView.setText("Score : "+score )

                    var vi = mSharedPref.getString(VIE, "")?.toInt()?.minus(1)
                    vieL.setText(vi.toString())
                    mSharedPref.edit().apply{
                        putString(SCORE,score.toString())
                        putString(VIE,vi.toString())

                    }.apply()
                    Toast.makeText(this@lvl4,"Wrong answer", Toast.LENGTH_SHORT).show()
                }
                finish();
                startActivity(getIntent());
            }

        }
        fun lvl4m()
        {
            bar2.progress = 0
            bar2.setEnabled(false)

            val apiInterface = ApiInterface.create()
            val map: HashMap<String, Number> = HashMap<String, Number>()
            var l = 1
            var b = 2

            map["level"] = 4

            apiInterface.getlevel(map).enqueue(object :
                Callback<Level> {
                override fun onResponse(
                    call: Call<Level>, response:
                    Response<Level>
                ) {
                    val lvl4: Level = response.body() as Level
                    Log.e("question: ", lvl4.questions[1])
                    var score = mSharedPref.getString(SCORE, "")
                    var score2 = 25
                    var score3 = 50
                    var score4 = 75
                    var score5 = 100
                    var score6 = 125
                    var score7 = 150
                    var score8 = 175
                    var score9 = 200
                    var score10 = 225
                    var score11 = 250


                    var scoreg = 250
                    if (score != null) {
                        if (lvl4 != null && score=="675") {
                            question.setText(lvl4.questions[0])
                            answer = lvl4.answers[0]
                            Log.e("question: ", lvl4.toString())
                            prog+=1
                            updateProgressBar()
                        }
                        if(lvl4 != null && score=="700"){
                            question.setText(lvl4.questions[1])
                            answer = lvl4.answers[1]
                            Log.e("question: ", lvl4.toString())
                            prog+=2
                            updateProgressBar()
                        }
                        if(lvl4 != null && score == "725"){
                            question.setText(lvl4.questions[2])
                            answer = lvl4.answers[2]
                            Log.e("question: ", lvl4.toString())
                            prog+=3
                            updateProgressBar()
                        }
                        if(lvl4 != null &&  score== "750"){
                            question.setText(lvl4.questions[3])
                            answer = lvl4.answers[3]
                            Log.e("question: ", lvl4.toString())
                            prog+=4
                            updateProgressBar()
                        }
                        if(lvl4 != null &&  score== "775"){
                            question.setText(lvl4.questions[4])
                            answer = lvl4.answers[4]
                            Log.e("question: ", lvl4.toString())
                            prog+=5
                            updateProgressBar()
                        }
                        if(lvl4 != null &&  score== "800"){
                            question.setText(lvl4.questions[5])
                            answer = lvl4.answers[5]
                            Log.e("question: ", lvl4.toString())
                            prog+=6
                            updateProgressBar()
                        }
                        if(lvl4 != null &&  score== "825"){
                            question.setText(lvl4.questions[6])
                            answer = lvl4.answers[6]
                            Log.e("question: ", lvl4.toString())
                            prog+=7
                            updateProgressBar()
                        }
                        if(lvl4 != null &&  score== "850"){
                            question.setText(lvl4.questions[7])
                            answer = lvl4.answers[7]
                            Log.e("question: ", lvl4.toString())
                            prog+=8
                            updateProgressBar()
                        }
                        if(lvl4 != null &&  score== "875"){
                            question.setText(lvl4.questions[8])
                            answer = lvl4.answers[8]
                            Log.e("question: ", lvl4.toString())
                            prog+=9
                            updateProgressBar()
                        }
                        if(lvl4 != null &&  score== "900") {  mSharedPref.edit().apply{
                            putString(SCORE,"900")
                            prog+=10
                            updateProgressBar()
                        }.apply()

                            val intent = Intent(this@lvl4, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }


                private fun updateProgressBar(){
                    bar2.progress = prog
                }
                override fun onFailure(call: Call<Level>
                                       , t: Throwable) {
                    Toast.makeText(this@lvl4,"could not fetch !!", Toast.LENGTH_SHORT).show()

                }
            })

        }


    }