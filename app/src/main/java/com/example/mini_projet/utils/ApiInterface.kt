package com.example.mini_projet.utils
import com.example.mini_projet.models.Level
import com.example.mini_projet.models.User
import com.example.mini_projet.models.Lvl1
import com.example.mini_projet.models.Ocm
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {

    @POST("/api/login")
    fun login(@Body map : HashMap<String, String> ): Call<User>



    @POST("/api/register")
    fun signup(@Body map : HashMap<String, String> ): Call<User>
    @PATCH("/api/user/profile")
    fun update(@Body map : HashMap<String, String> ): Call<User>
    @GET ("/api/level/getall")
    fun lvl1Question(): Call<ArrayList<Level>>
    @POST ("/api/level/")
    fun getlevel(@Body map : HashMap<String, Number>): Call<Level>
    @POST ("/api/level/check-pass")
    fun checkpass(@Body map : HashMap<String, Number>): Call<Level>
    @POST("/api/feature")
    fun ocm(@Body map : HashMap<String, String> ): Call<Ocm>
   /*
    @GET ("/api/lvl1/score")
    fun scoreFUN(): Call<MutableList<String>>
*/

    companion object {
        //var BASE_URL = "http://192.168.1.16:5000"
        var BASE_URL = "http://172.17.2.116:3000"
        // var BASE_URL = "https://localhost:3000"
        //var BASE_URL = "http://172.17.13.29:5000"
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

}