package com.madinafinal.madinaeshop

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.madinafinal.madinaeshop.databinding.ActivityLoginBinding
import com.madinafinal.madinaeshop.model.UserModel

class LoginActivity : AppCompatActivity() {
    private var userName: String?= null
    private var userPhone: String?= null
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var googleSignInClient: GoogleSignInClient

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // for google signin val
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("724033937918-m1mqasc0iojh00iae8s3su9lshh60ce0.apps.googleusercontent.com")
            .requestEmail().build()

        auth = Firebase.auth

        //initialize firebase database
        database = Firebase.database.reference
       // database = Firebase.database.reference.database


        //initialize Google SignIn Client
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        binding.loginButton.setOnClickListener {
            //get data from text filed

            email = binding.emailAddress.text.toString()
            password = binding.password.text.toString()

            if (email.isBlank()|| password.isBlank()){
                Toast.makeText(this, "please fill the all details", Toast.LENGTH_SHORT).show()
            }else{

                createUser()

            }

        }

        binding.googleButton.setOnClickListener {
            val signIntent = googleSignInClient.signInIntent
            launcher.launch(signIntent)
        }


        binding.RagisterButtonText.setOnClickListener {
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.LocationTaxt.setOnClickListener {
            val intent = Intent(this, ChooseLocationActivity::class.java)
            startActivity(intent)
        }


    }

    private fun createUser() {
       auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {task->
           if (task.isSuccessful){
               val user = auth.currentUser
               updateUi(user)
               Toast.makeText(this, "Account Login Successfully", Toast.LENGTH_SHORT).show()
           }else{
               auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
                   if (task.isSuccessful){
                       saveUserdata()
                       val user = auth.currentUser
                       updateUi(user)
                       Toast.makeText(this, "Account Create & Login Successfully", Toast.LENGTH_SHORT).show()
                   }else{
                       Toast.makeText(this, "আপনার একাউন্ট টি তৈরি হতে ব্যার্থ হয়েছে", Toast.LENGTH_SHORT).show()
                       Log.d("Account", "createAccount: Failure",task.exception)
                   }
               }

           }
       }
    }

    private fun saveUserdata() {
        //retrieve data from input filed
        email = binding.emailAddress.text.toString()
        password = binding.password.text.toString().trim()

        val user= UserModel(userName,userPhone,email,password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        //save data to Firebase database
        database.child("user").child(userId).setValue(user)
    }

    private fun updateUi(user: FirebaseUser?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    // launcher for google sign in
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if (task.isSuccessful) {
                    val account: GoogleSignInAccount = task.result

                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                    auth.signInWithCredential(credential).addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            // successfully sign in with google
                            Toast.makeText(
                                this, "Successfully sign-in with google😊✨", Toast.LENGTH_SHORT
                            ).show()
                           startActivity(Intent(this,MainActivity::class.java))
                        } else {
                            Toast.makeText(this, "Google Sign-in failed😔", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Google Sign-in failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    // Check if user allredy loged in
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser!=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


}