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

class lvl7 : AppCompatActivity() {
    lateinit var answer : String
    private lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lvl7)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Log.e("score",mSharedPref.getString(SCORE, "").toString())
        textView.setText("Score : "+mSharedPref.getString(SCORE, "").toString() )
        vieL.setText(mSharedPref.getString(VIE, "").toString())
        //  mSharedPref.getString(AVATAR, "").toString()
        lvl7m()
        btnsignup2.setOnClickListener {
            if(answer == emailid3.text.toString())
            {
                var score = mSharedPref.getString(SCORE, "")?.toInt()?.plus(25)
                textView.setText("Score : "+score )

                mSharedPref.edit().apply{
                    putString(SCORE,score.toString())
                }.apply()
                Toast.makeText(this@lvl7,"Good", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@lvl7,"Wrong answer", Toast.LENGTH_SHORT).show()
            }
            finish();
            startActivity(getIntent());
        }

    }
    fun lvl7m()
    {
        val apiInterface = ApiInterface.create()
        val map: HashMap<String, Number> = HashMap<String, Number>()
        var l = 1
        var b = 2

        map["level"] = 7

        apiInterface.getlevel(map).enqueue(object :
            Callback<Level> {
            override fun onResponse(
                call: Call<Level>, response:
                Response<Level>
            ) {
                val lvl7: Level = response.body() as Level
                Log.e("question: ", lvl7.questions[1])
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
                    if (lvl7 != null && score=="1350") {
                        question.setText(lvl7.questions[0])
                        answer = lvl7.answers[0]
                        Log.e("question: ", lvl7.toString())
                    }
                    if(lvl7 != null && score=="1375"){
                        question.setText(lvl7.questions[1])
                        answer = lvl7.answers[1]
                        Log.e("question: ", lvl7.toString())

                    }
                    if(lvl7 != null && score == "1400"){
                        question.setText(lvl7.questions[2])
                        answer = lvl7.answers[2]
                        Log.e("question: ", lvl7.toString())

                    }
                    if(lvl7 != null &&  score== "1425"){
                        question.setText(lvl7.questions[3])
                        answer = lvl7.answers[3]
                        Log.e("question: ", lvl7.toString())

                    }
                    if(lvl7 != null &&  score== "1450"){
                        question.setText(lvl7.questions[4])
                        answer = lvl7.answers[4]
                        Log.e("question: ", lvl7.toString())

                    }
                    if(lvl7 != null &&  score== "1475"){
                        question.setText(lvl7.questions[5])
                        answer = lvl7.answers[5]
                        Log.e("question: ", lvl7.toString())

                    }
                    if(lvl7 != null &&  score== "1500"){
                        question.setText(lvl7.questions[6])
                        answer = lvl7.answers[6]
                        Log.e("question: ", lvl7.toString())

                    }
                    if(lvl7 != null &&  score== "1525"){
                        question.setText(lvl7.questions[7])
                        answer = lvl7.answers[7]
                        Log.e("question: ", lvl7.toString())

                    }
                    if(lvl7 != null &&  score== "1550"){
                        question.setText(lvl7.questions[8])
                        answer = lvl7.answers[8]
                        Log.e("question: ", lvl7.toString())

                    }
                    if(lvl7 != null &&  score== "1575") {  mSharedPref.edit().apply{
                        putString(SCORE,"1575")
                    }.apply()

                        val intent = Intent(this@lvl7, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }


            override fun onFailure(call: Call<Level>
                                   , t: Throwable) {
                Toast.makeText(this@lvl7,"could not fetch !!", Toast.LENGTH_SHORT).show()

            }
        })

    }


}