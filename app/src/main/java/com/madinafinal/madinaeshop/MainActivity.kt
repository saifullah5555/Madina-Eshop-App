package com.madinafinal.madinaeshop

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.madinafinal.madinaeshop.OtherFragment.Notifycation_Bottom_Fragment
import com.madinafinal.madinaeshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)






        // আপনার অ্যাপের ডেটা লোড করুন
        var NavController= findNavController(R.id.fragmentContainerView)
        var bottomnav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomnav.setupWithNavController(NavController)
        binding.notificationButton.setOnClickListener {
            val BottomShedDyalog = Notifycation_Bottom_Fragment()
            BottomShedDyalog.show(supportFragmentManager,"test")
        }










    }
}