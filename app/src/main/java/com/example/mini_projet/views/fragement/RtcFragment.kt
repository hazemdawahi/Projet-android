package com.example.mini_projet.views.fragement
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mini_projet.*
import com.example.mini_projet.databinding.ActivityEditProfileBinding.inflate
import com.example.mini_projet.models.Ocm
import com.example.mini_projet.models.User
import com.example.mini_projet.utils.ApiInterface
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.rtc.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class RtcFragment :  Fragment()  {
    private var selectedImageUri: Uri? = null
    lateinit var storage: FirebaseStorage
    val formater = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
    val now = Date()
    val fileName = formater.format(now)

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.rtc, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //fireBase
        storage = Firebase.storage
        profilePicOrc.setOnClickListener {
            openGallery()
        }

        Try.setOnClickListener {
            uploadImage()
            val apiInterface = ApiInterface.create()
            val map: HashMap<String, String> = HashMap()
            map["image"] = "https://firebasestorage.googleapis.com/v0/b/mini-projet-2e934.appspot.com/o/images%2F"+fileName.toString()+"?alt=media"
            map["langue"] = language.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    apiInterface.ocm(map).enqueue(object :
                        Callback<Ocm> {
                        override fun onResponse(
                            call: Call<Ocm>, response:
                            Response<Ocm>
                        ) {
                            val otc = response.body()
                            if (otc != null) {
                                translate.setText(otc.newStr.toString())
                                Log.e("ocm", otc.toString())
                            } else {
                                Toast.makeText(context, "error!", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<Ocm>, t: Throwable) {
                            Toast.makeText(context, "Connexion error!", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
        }

        super.onViewCreated(view, savedInstanceState)
    }
    private fun uploadImage()
    {
        if (selectedImageUri == null) {
            Toast.makeText(context,"Please Select Picture", Toast.LENGTH_SHORT).show()
        }
        else
        {
            val progressDialog = ProgressDialog(context)
            progressDialog.setMessage("Uploading Image ...")
            progressDialog.setCancelable(false)
            progressDialog.show()
            val storageReference = FirebaseStorage.getInstance().reference.child("images/$fileName")
            storageReference.putFile(selectedImageUri!!).
            addOnSuccessListener {
                profilePicOrc.setImageURI(selectedImageUri)
                if(progressDialog.isShowing)
                {
                    progressDialog.dismiss()
                }
                Toast.makeText(context,"Successfuly uploaded", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                if(progressDialog.isShowing)
                {
                    progressDialog.dismiss()
                }
                Toast.makeText(context,"Sorry", Toast.LENGTH_SHORT).show()

            }
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && resultCode == AppCompatActivity.RESULT_OK)
        {
            selectedImageUri = data?.data!!
            profilePicOrc.setImageURI(selectedImageUri)
        }
    }
    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,100)
    }
}