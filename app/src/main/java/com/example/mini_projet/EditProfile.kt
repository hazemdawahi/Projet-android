package com.example.mini_projet

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build.ID
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.mini_projet.models.User
import com.example.mini_projet.utils.ApiInterface
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_edit_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class EditProfile : AppCompatActivity() {
    lateinit var UsernameShow: TextInputEditText
    lateinit var PasswordShow : TextInputEditText
    private lateinit var mSharedPref: SharedPreferences
    private  var btnModifier : Button? = null
    private  var btnupload : Button? = null
    private var profilePic: ImageView? = null
    private var selectedImageUri: Uri? = null
    lateinit var storage: FirebaseStorage
    val formater = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
    val now = Date()
     val fileName = formater.format(now)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        //image
       val toolbar: Toolbar = findViewById(R.id.app_bar)

        toolbar.setTitle("")
        var DesignImage = findViewById<CardView>(R.id.DesignImage)
        DesignImage.visibility = View.INVISIBLE
        toolbar.setNavigationIcon(R.drawable.ic_return)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        //fireBase
        storage = Firebase.storage
        btnupload = findViewById(R.id.upload)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        UsernameShow = findViewById(R.id.UsernameShow)
        PasswordShow = findViewById(R.id.PasswordShow)
        profilePic = findViewById(R.id.profilePic)
        if(mSharedPref.getString(AVATAR, "").toString()=="No Picture")
        {
            profilePic!!.setImageResource(R.drawable.ic_perso)
        }
        else
        {
            val filename2 = mSharedPref.getString(AVATAR, "").toString()
            val path = "https://firebasestorage.googleapis.com/v0/b/mini-projet-2e934.appspot.com/o/images%2F"+filename2+"?alt=media"
            Glide.with(this)
                .load(path)
                .into(profilePic)
        }

        ////************************

        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        UsernameShow = findViewById(R.id.UsernameShow)
        PasswordShow = findViewById(R.id.PasswordShow)
        UsernameShow.setText(mSharedPref.getString(USERNAME, "").toString())
        PasswordShow.setText(mSharedPref.getString(NAME, "").toString())
        btnModifier = findViewById(R.id.Modifier)

        /// btn
       profilePic!!.setOnClickListener {
            openGallery()
        }

        btnupload!!.setOnClickListener {
            uploadImage()
        }


        btnModifier!!.setOnClickListener {
            if(doUpdate())
            {
                val apiInterface = ApiInterface.create()
                val map: HashMap<String, String> = HashMap()
                map["iduser"] = mSharedPref.getString(IDE, "").toString()
                Log.e("user id sh",mSharedPref.getString(IDE, "").toString())
                map["name"] = PasswordShow.text.toString()
                map["email"] = UsernameShow.text.toString()
             /*    apiInterface.update(map).enqueue(object :
                Callback<User> {

              */
              if (selectedImageUri == null) {
                    map["avatar"] = mSharedPref.getString(AVATAR, "").toString()
                }
                else
                    map["avatar"] = fileName.toString()
                Log.e("iduser",map["iduser"].toString())
                Log.e("image",map["avatar"].toString())
                Log.e("name",map["name"].toString())
                Log.e("email",map["email"].toString())
                apiInterface.update(map).enqueue(object :
                    Callback<User> {
                    override fun onResponse(call: Call<User>, response:
                    Response<User>
                    ) {
                        val user = response.body()
                        if(user !=null)
                        {
                            mSharedPref.edit().apply{
                                putString(ID,user._id.toString())
                                putString(USERNAME, user.name)
                                putString(PASSWORD, user.password)
                               putString(AVATAR, user.avatar)

                            }.apply()
                            val intent = Intent(this@EditProfile, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else
                        {
                            Toast.makeText(this@EditProfile,"error!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(this@EditProfile,"Connexion error!", Toast.LENGTH_SHORT).show()
                    }
                })
            }

        }
    }
    private fun doUpdate(): Boolean
    {
        UsernameTextLayoutEdit.error = null
        PasswordTextLayoutEdit.error = null
        if(UsernameShow.text!!.isEmpty())
        {
            UsernameTextLayoutEdit.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        if(PasswordShow.text!!.isEmpty())
        {
            PasswordTextLayoutEdit.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        return true
    }
    // fun upload image

    private fun uploadImage()
    {
        if (selectedImageUri == null) {
            Toast.makeText(this@EditProfile,"Please Select Picture", Toast.LENGTH_SHORT).show()
        }
        else
        {
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Uploading Image ...")
            progressDialog.setCancelable(false)
            progressDialog.show()
            val storageReference = FirebaseStorage.getInstance().reference.child("images/$fileName")
            storageReference.putFile(selectedImageUri!!).
            addOnSuccessListener {
                profilePic!!.setImageURI(selectedImageUri)
                if(progressDialog.isShowing)
                {
                    progressDialog.dismiss()
                }
                Toast.makeText(this,"Successfuly uploaded", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                if(progressDialog.isShowing)
                {
                    progressDialog.dismiss()
                }
                Toast.makeText(this,"Sorry", Toast.LENGTH_SHORT).show()

            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && resultCode == RESULT_OK)
        {
            selectedImageUri = data?.data!!
            profilePic!!.setImageURI(selectedImageUri)
        }
    }
    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,100)
    }


    override fun onCreateOptionsMenu(menu2: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu2, menu2)
        return super.onCreateOptionsMenu(menu2)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.logout ->{
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.logoutTitle))
                builder.setMessage(R.string.logoutMessage)
                builder.setPositiveButton("Yes"){ dialogInterface, which ->
                    getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit().clear().apply()
                    finish()
                }
                builder.setNegativeButton("No"){dialogInterface, which ->
                    dialogInterface.dismiss()
                }
                builder.create().show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}