package com.madinafinal.madinaeshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.madinafinal.madinaeshop.databinding.ActivityCalenderBinding

class CalenderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalenderBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCalenderBinding.inflate(layoutInflater)






        super.onCreate(savedInstanceState)
        setContentView(binding.root)



    }
}