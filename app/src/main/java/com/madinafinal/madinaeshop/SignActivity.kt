package com.madinafinal.madinaeshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.madinafinal.madinaeshop.databinding.ActivitySignBinding
import com.madinafinal.madinaeshop.model.UserModel

class SignActivity : AppCompatActivity() {

    private lateinit var name: String
    private lateinit var email: String
    private lateinit var phone: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var googleSignInClient: GoogleSignInClient

    private val binding: ActivitySignBinding by lazy {
        ActivitySignBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("724033937918-m1mqasc0iojh00iae8s3su9lshh60ce0.apps.googleusercontent.com").requestEmail().build()
        //initialize firebase auth
        auth = Firebase.auth

        //initialize firebase database
        database = Firebase.database.reference


        //initialize Google SignIn Client
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        binding.createAccountButton.setOnClickListener {
            name = binding.userName.text.toString()
            phone = binding.userPhone.text.toString().trim()
            email = binding.userEmail.text.toString().trim()
            password = binding.userPassword.text.toString().trim()

            if (name.isBlank()||phone.isBlank() || email.isBlank()||password.isBlank()){
                Toast.makeText(this, "please fill the all details", Toast.LENGTH_SHORT).show()
            }else{
                createAccount(email,password)
            }
        }

        binding.BackLoginText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {task ->
            if (task.isSuccessful){
                Toast.makeText(this, "আপনার একাউন্ট টি সফলভাবে তৈরি হয়েছে", Toast.LENGTH_SHORT).show()
                saveUserData()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "আপনার একাউন্ট টি তৈরি হতে ব্যার্থ হয়েছে", Toast.LENGTH_SHORT).show()
                Log.d("Account", "createAccount: Failure",task.exception)
            }


        }
    }

    private fun saveUserData() {
        //retrieve data from input filed
        name = binding.userName.text.toString()
        phone = binding.userPhone.text.toString().trim()
        email = binding.userEmail.text.toString().trim()
        password = binding.userPassword.text.toString().trim()

        val user= UserModel(name,phone,email,password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        //save data to Firebase database
        database.child("user").child(userId).setValue(user)
    }
}