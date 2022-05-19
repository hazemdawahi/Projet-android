package com.example.mini_projet.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Level (
    @SerializedName("_id") val _id : String,
    @SerializedName("nbrQuestions") val nbrQuestions : Int,
    @SerializedName("questions") val questions : MutableList<String>,
    @SerializedName("answers") val answers : MutableList<String>
) : Serializable