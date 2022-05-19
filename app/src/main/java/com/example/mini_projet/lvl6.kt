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

class lvl6 : AppCompatActivity() {
    lateinit var answer : String
    private lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lvl6)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Log.e("score",mSharedPref.getString(SCORE, "").toString())
        textView.setText("Score : "+mSharedPref.getString(SCORE, "").toString() )
        vieL.setText(mSharedPref.getString(VIE, "").toString())
        //  mSharedPref.getString(AVATAR, "").toString()
        lvl6m()
        btnsignup2.setOnClickListener {
            if(answer == emailid3.text.toString())
            {
                var score = mSharedPref.getString(SCORE, "")?.toInt()?.plus(25)
                textView.setText("Score : "+score )

                mSharedPref.edit().apply{
                    putString(SCORE,score.toString())
                }.apply()
                Toast.makeText(this@lvl6,"Good", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@lvl6,"Wrong answer", Toast.LENGTH_SHORT).show()
            }
            finish();
            startActivity(getIntent());
        }

    }
    fun lvl6m()
    {
        val apiInterface = ApiInterface.create()
        val map: HashMap<String, Number> = HashMap<String, Number>()
        var l = 1
        var b = 2

        map["level"] = 6

        apiInterface.getlevel(map).enqueue(object :
            Callback<Level> {
            override fun onResponse(
                call: Call<Level>, response:
                Response<Level>
            ) {
                val lvl6: Level = response.body() as Level
                Log.e("question: ", lvl6.questions[1])
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
                    if (lvl6 != null && score=="1125") {
                        question.setText(lvl6.questions[0])
                        answer = lvl6.answers[0]
                        Log.e("question: ", lvl6.toString())
                    }
                    if(lvl6 != null && score=="1150"){
                        question.setText(lvl6.questions[1])
                        answer = lvl6.answers[1]
                        Log.e("question: ", lvl6.toString())

                    }
                    if(lvl6 != null && score == "1175"){
                        question.setText(lvl6.questions[2])
                        answer = lvl6.answers[2]
                        Log.e("question: ", lvl6.toString())

                    }
                    if(lvl6 != null &&  score== "1200"){
                        question.setText(lvl6.questions[3])
                        answer = lvl6.answers[3]
                        Log.e("question: ", lvl6.toString())

                    }
                    if(lvl6 != null &&  score== "1225"){
                        question.setText(lvl6.questions[4])
                        answer = lvl6.answers[4]
                        Log.e("question: ", lvl6.toString())

                    }
                    if(lvl6 != null &&  score== "1250"){
                        question.setText(lvl6.questions[5])
                        answer = lvl6.answers[5]
                        Log.e("question: ", lvl6.toString())

                    }
                    if(lvl6 != null &&  score== "1275"){
                        question.setText(lvl6.questions[6])
                        answer = lvl6.answers[6]
                        Log.e("question: ", lvl6.toString())

                    }
                    if(lvl6 != null &&  score== "1300"){
                        question.setText(lvl6.questions[7])
                        answer = lvl6.answers[7]
                        Log.e("question: ", lvl6.toString())

                    }
                    if(lvl6 != null &&  score== "1325"){
                        question.setText(lvl6.questions[8])
                        answer = lvl6.answers[8]
                        Log.e("question: ", lvl6.toString())

                    }
                    if(lvl6 != null &&  score== "1350") {  mSharedPref.edit().apply{
                        putString(SCORE,"1350")
                    }.apply()

                        val intent = Intent(this@lvl6, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }


            override fun onFailure(call: Call<Level>
                                   , t: Throwable) {
                Toast.makeText(this@lvl6,"could not fetch !!", Toast.LENGTH_SHORT).show()

            }
        })

    }


}