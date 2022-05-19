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
import kotlinx.android.synthetic.main.activity_lvl4.*
import kotlinx.android.synthetic.main.activity_lvl5.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class lvl5 : AppCompatActivity() {
    private var prog = 0
    lateinit var answer : String
    private lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lvl5)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Log.e("score",mSharedPref.getString(SCORE, "").toString())
        textView.setText("Score : "+mSharedPref.getString(SCORE, "").toString() )
        vieL.setText(mSharedPref.getString(VIE, "").toString())
        //  mSharedPref.getString(AVATAR, "").toString()
        lvl5m()
        btnsignup2.setOnClickListener {
            if(answer == emailid3.text.toString())
            {
                var score = mSharedPref.getString(SCORE, "")?.toInt()?.plus(25)
                textView.setText("Score : "+score )

                mSharedPref.edit().apply{
                    putString(SCORE,score.toString())
                }.apply()
                Toast.makeText(this@lvl5,"Good", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@lvl5,"Wrong answer", Toast.LENGTH_SHORT).show()
            }
            finish();
            startActivity(getIntent());
        }

    }
    fun lvl5m()
    {
        bar3.progress = 0
        bar3.setEnabled(false)

        val apiInterface = ApiInterface.create()
        val map: HashMap<String, Number> = HashMap<String, Number>()
        var l = 1
        var b = 2

        map["level"] = 5

        apiInterface.getlevel(map).enqueue(object :
            Callback<Level> {
            override fun onResponse(
                call: Call<Level>, response:
                Response<Level>
            ) {
                val lvl5: Level = response.body() as Level
                Log.e("question: ", lvl5.questions[1])
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
                    if (lvl5 != null && score=="900") {
                        question.setText(lvl5.questions[0])
                        answer = lvl5.answers[0]
                        Log.e("question: ", lvl5.toString())
                        prog+=1
                        updateProgressBar()
                    }
                    if(lvl5 != null && score=="925"){
                        question.setText(lvl5.questions[1])
                        answer = lvl5.answers[1]
                        Log.e("question: ", lvl5.toString())
                        prog+=2
                        updateProgressBar()
                    }
                    if(lvl5 != null && score == "950"){
                        question.setText(lvl5.questions[2])
                        answer = lvl5.answers[2]
                        Log.e("question: ", lvl5.toString())
                        prog+=3
                        updateProgressBar()
                    }
                    if(lvl5 != null &&  score== "975"){
                        question.setText(lvl5.questions[3])
                        answer = lvl5.answers[3]
                        Log.e("question: ", lvl5.toString())
                        prog+=4
                        updateProgressBar()
                    }
                    if(lvl5 != null &&  score== "1000"){
                        question.setText(lvl5.questions[4])
                        answer = lvl5.answers[4]
                        Log.e("question: ", lvl5.toString())
                        prog+=5
                        updateProgressBar()
                    }
                    if(lvl5 != null &&  score== "1025"){
                        question.setText(lvl5.questions[5])
                        answer = lvl5.answers[5]
                        Log.e("question: ", lvl5.toString())
                        prog+=6
                        updateProgressBar()
                    }
                    if(lvl5 != null &&  score== "1050"){
                        question.setText(lvl5.questions[6])
                        answer = lvl5.answers[6]
                        Log.e("question: ", lvl5.toString())
                        prog+=7
                        updateProgressBar()
                    }
                    if(lvl5 != null &&  score== "1075"){
                        question.setText(lvl5.questions[7])
                        answer = lvl5.answers[7]
                        Log.e("question: ", lvl5.toString())
                        prog+=8
                        updateProgressBar()
                    }
                    if(lvl5 != null &&  score== "1100"){
                        question.setText(lvl5.questions[8])
                        answer = lvl5.answers[8]
                        Log.e("question: ", lvl5.toString())
                        prog+=9
                        updateProgressBar()
                    }
                    if(lvl5 != null &&  score== "1125") {  mSharedPref.edit().apply{
                        putString(SCORE,"1125")
                        prog+=10
                        updateProgressBar()
                    }.apply()

                        val intent = Intent(this@lvl5, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }

            private fun updateProgressBar(){
                bar3.progress = prog
            }
            override fun onFailure(call: Call<Level>
                                   , t: Throwable) {
                Toast.makeText(this@lvl5,"could not fetch !!", Toast.LENGTH_SHORT).show()

            }
        })

    }


}