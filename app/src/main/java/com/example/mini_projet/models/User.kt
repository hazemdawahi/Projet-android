package com.example.mini_projet.models
import  com.google.gson.annotations.SerializedName
import java.io.Serializable

    data class User  (
        @SerializedName("_id") val _id : String,
        @SerializedName("password") val password : String,
        @SerializedName("email") val email : String,
        @SerializedName("name") val name : String,
        @SerializedName("avatar") val avatar : String,
        @SerializedName("scores") val scores : MutableList<Integer>,
        @SerializedName("vie") val vie : Integer
        ) : Serializable
