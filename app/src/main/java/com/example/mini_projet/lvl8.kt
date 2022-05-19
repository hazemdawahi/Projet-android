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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class lvl8 : AppCompatActivity() {
    lateinit var answer : String
    private lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lvl8)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Log.e("score",mSharedPref.getString(SCORE, "").toString())
        textView.setText("Score : "+mSharedPref.getString(SCORE, "").toString() )
        vieL.setText(mSharedPref.getString(VIE, "").toString())
        //  mSharedPref.getString(AVATAR, "").toString()
        lvl8m()
        btnsignup2.setOnClickListener {
            if(answer == emailid3.text.toString())
            {
                var score = mSharedPref.getString(SCORE, "")?.toInt()?.plus(25)
                textView.setText("Score : "+score )

                mSharedPref.edit().apply{
                    putString(SCORE,score.toString())
                }.apply()
                Toast.makeText(this@lvl8,"Good", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@lvl8,"Wrong answer", Toast.LENGTH_SHORT).show()
            }
            finish();
            startActivity(getIntent());
        }

    }
    fun lvl8m()
    {
        val apiInterface = ApiInterface.create()
        val map: HashMap<String, Number> = HashMap<String, Number>()
        var l = 1
        var b = 2

        map["level"] = 8

        apiInterface.getlevel(map).enqueue(object :
            Callback<Level> {
            override fun onResponse(
                call: Call<Level>, response:
                Response<Level>
            ) {
                val lvl8: Level = response.body() as Level
                Log.e("question: ", lvl8.questions[1])
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
                    if (lvl8 != null && score=="1575") {
                        question.setText(lvl8.questions[0])
                        answer = lvl8.answers[0]
                        Log.e("question: ", lvl8.toString())
                    }
                    if(lvl8 != null && score=="1600"){
                        question.setText(lvl8.questions[1])
                        answer = lvl8.answers[1]
                        Log.e("question: ", lvl8.toString())

                    }
                    if(lvl8 != null && score == "1625"){
                        question.setText(lvl8.questions[2])
                        answer = lvl8.answers[2]
                        Log.e("question: ", lvl8.toString())

                    }
                    if(lvl8 != null &&  score== "1650"){
                        question.setText(lvl8.questions[3])
                        answer = lvl8.answers[3]
                        Log.e("question: ", lvl8.toString())

                    }
                    if(lvl8 != null &&  score== "1675"){
                        question.setText(lvl8.questions[4])
                        answer = lvl8.answers[4]
                        Log.e("question: ", lvl8.toString())

                    }
                    if(lvl8 != null &&  score== "1700"){
                        question.setText(lvl8.questions[5])
                        answer = lvl8.answers[5]
                        Log.e("question: ", lvl8.toString())

                    }
                    if(lvl8 != null &&  score== "1725"){
                        question.setText(lvl8.questions[6])
                        answer = lvl8.answers[6]
                        Log.e("question: ", lvl8.toString())

                    }
                    if(lvl8 != null &&  score== "1750"){
                        question.setText(lvl8.questions[7])
                        answer = lvl8.answers[7]
                        Log.e("question: ", lvl8.toString())

                    }
                    if(lvl8 != null &&  score== "1775"){
                        question.setText(lvl8.questions[8])
                        answer = lvl8.answers[8]
                        Log.e("question: ", lvl8.toString())

                    }
                    if(lvl8 != null &&  score== "1800") {  mSharedPref.edit().apply{
                        putString(SCORE,"1800")
                    }.apply()

                        val intent = Intent(this@lvl8, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }


            override fun onFailure(call: Call<Level>
                                   , t: Throwable) {
                Toast.makeText(this@lvl8,"could not fetch !!", Toast.LENGTH_SHORT).show()

            }
        })

    }


}