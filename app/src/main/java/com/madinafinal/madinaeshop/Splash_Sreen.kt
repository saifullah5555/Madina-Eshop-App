package com.madinafinal.madinaeshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth


class Splash_Sreen : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_sreen)
        Handler(Looper.getMainLooper()).postDelayed({
            // jibone prothom if else er bebohar korlam ebong 2 bar cesta korar por allahor rohmote sofol holo, alhamsulillah
            var auth = auth.currentUser?.uid
            if (auth != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, StartActivity::class.java)
                startActivity(intent)
            }

            finish()
        }, 3000)
    }
}