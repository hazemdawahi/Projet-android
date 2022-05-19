package com.example.mini_projet.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Ocm  (
    @SerializedName("newStr") val newStr : String,
) : Serializable