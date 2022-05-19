

package com.example.mini_projet.models

import  com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Lvl1 (
    @SerializedName("id") val id : String,
    @SerializedName("question1") val question1 : String,
    @SerializedName("answer1") val answer1 : String,
    @SerializedName("question2") val question2 : String,
    @SerializedName("answer2") val answer2 : String,
    @SerializedName("question3") val question3 : String,
    @SerializedName("answer3") val answer3 : String,
    @SerializedName("question4") val question4 : String,
    @SerializedName("answer4") val answer4 : String,
    @SerializedName("question5") val question5 : String,
    @SerializedName("answer5") val answer5 : String,
    @SerializedName("question6") val question6 : String,
    @SerializedName("answer6") val answer6 : String,
    @SerializedName("question7") val question7 : String,
    @SerializedName("answer7") val answer7 : String,
    @SerializedName("question8") val question8 : String,
    @SerializedName("answer8") val answer8 : String,
    @SerializedName("question9") val question9 : String,
    @SerializedName("answer9") val answer9 : String,
    @SerializedName("question10") val question10 : String,
    @SerializedName("answer10") val answer10 : String,
    @SerializedName("nbrstore") val nbrstore : Number,
    ) : Serializable