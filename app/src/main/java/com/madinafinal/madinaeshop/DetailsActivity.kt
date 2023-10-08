package com.madinafinal.madinaeshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madinafinal.madinaeshop.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

       //for back button
        binding.DetailBackButton.setOnClickListener {
            finish()
        }


        val forename= intent.getStringExtra("MenuItemName")
        val foreimag= intent.getIntExtra("MenuItemImage",0)
        binding.DetailFoodName.text = forename
        binding.DetailFoodImage.setImageResource(foreimag)

    }

}