package com.example.mini_projet

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.mini_projet.models.Level
import com.example.mini_projet.models.Lvl1
import com.example.mini_projet.utils.ApiInterface
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_lvl1.*
import kotlinx.android.synthetic.main.activity_lvl1.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.HashMap


class lvl1 : AppCompatActivity() {
lateinit var answer : String
    private var prog = 0
     lateinit var tts : TextToSpeech
    private lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lvl1)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Log.e("score",mSharedPref.getString(SCORE, "").toString())
        textView.setText("Score : "+mSharedPref.getString(SCORE, "").toString() )
        vieL.setText(mSharedPref.getString(VIE, "").toString())
      //  mSharedPref.getString(AVATAR, "").toString()
lvl1m()
        logoPic10.setOnClickListener{
tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
    if(it==TextToSpeech.SUCCESS)
    {
        tts.setSpeechRate(1.0f)
        tts.speak(question.text.toString(),TextToSpeech.QUEUE_ADD,null)
    }
})
        }
        btnsignup2.setOnClickListener {
            if(answer == emailid3.text.toString())
            {
                var score = mSharedPref.getString(SCORE, "")?.toInt()?.plus(25)
                textView.setText("Score : "+score )

                mSharedPref.edit().apply{
                    putString(SCORE,score.toString())
                }.apply()
                Toast.makeText(this@lvl1,"Good",Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@lvl1,"Wrong answer",Toast.LENGTH_SHORT).show()
            }
            finish();
            startActivity(getIntent());
        }


    }


    fun lvl1m()
    {
        seekBar.progress = 0
        seekBar.setEnabled(false)
        val apiInterface = ApiInterface.create()
        val map: HashMap<String, Number> = HashMap<String, Number>()
           var l = 1
        var b = 2

        map["level"] = 1

        apiInterface.getlevel(map).enqueue(object :
                Callback<Level> {
            override fun onResponse(
                call: Call<Level>, response:
                Response<Level>
            ) {
                val lvl1: Level = response.body() as Level
                Log.e("question: ", lvl1.questions[1])
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
                    if (lvl1 != null && score=="0") {
                        question.setText(lvl1.questions[0])
                        answer = lvl1.answers[0]
                        Log.e("question: ", lvl1.toString())
                        prog+=2
                        updateProgressBar()
                    }
                    if(lvl1 != null && score=="25"){
                        question.setText(lvl1.questions[1])
                        answer = lvl1.answers[1]
                        Log.e("question: ", lvl1.toString())
                        prog+=3
                        updateProgressBar()
                    }
                    if(lvl1 != null && score == "50"){
                        question.setText(lvl1.questions[2])
                        answer = lvl1.answers[2]
                        Log.e("question: ", lvl1.toString())

                    }
                     if(lvl1 != null &&  score== "75"){
                        question.setText(lvl1.questions[3])
                        answer = lvl1.answers[3]
                        Log.e("question: ", lvl1.toString())
                         prog+=4
                         updateProgressBar()
                    }
                    if(lvl1 != null &&  score== "100"){
                        question.setText(lvl1.questions[4])
                        answer = lvl1.answers[4]
                        Log.e("question: ", lvl1.toString())
                        prog+=5
                        updateProgressBar()
                    }
                    if(lvl1 != null &&  score== "125"){
                        question.setText(lvl1.questions[5])
                        answer = lvl1.answers[5]
                        Log.e("question: ", lvl1.toString())
                        prog+=6
                        updateProgressBar()
                    }
                    if(lvl1 != null &&  score== "150"){
                        question.setText(lvl1.questions[6])
                        answer = lvl1.answers[6]
                        Log.e("question: ", lvl1.toString())
                        prog+=7
                        updateProgressBar()
                    }
                    if(lvl1 != null &&  score== "175"){
                        question.setText(lvl1.questions[7])
                        answer = lvl1.answers[7]
                        Log.e("question: ", lvl1.toString())
                        prog+=8
                        updateProgressBar()
                    }
                    if(lvl1 != null &&  score== "200"){
                        question.setText(lvl1.questions[8])
                        answer = lvl1.answers[8]
                        Log.e("question: ", lvl1.toString())
                        prog+=9
                        updateProgressBar()
                    }
                    if(lvl1 != null &&  score== "225") {  mSharedPref.edit().apply{
                            putString(SCORE,"225")
                        prog+=10
                        updateProgressBar()
                        }.apply()

                        val intent = Intent(this@lvl1, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }

  private fun updateProgressBar(){
      seekBar.progress = prog
  }

            override fun onFailure(call: Call<Level>
                , t: Throwable) {
                    Toast.makeText(this@lvl1,"could not fetch !!",Toast.LENGTH_SHORT).show()

                }
            })

    }

}
