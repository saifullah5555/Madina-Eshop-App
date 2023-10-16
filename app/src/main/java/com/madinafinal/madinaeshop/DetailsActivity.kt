package com.madinafinal.madinaeshop

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.madinafinal.madinaeshop.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    private var foodName: String? = null
    private var foodImage: String? = null
    private var foodsDecription: String? = null
    private var foodIngradint: String? = null
    private var foodPrice: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodName = intent.getStringExtra("menufoodName")
        foodsDecription = intent.getStringExtra("decriptionTextView")
        foodIngradint = intent.getStringExtra("ingradianTextView")
        foodPrice = intent.getStringExtra("menuitemprice")
        foodImage = intent.getStringExtra("menuimage")

        with(binding) {
            DetailFoodName.text = foodName
            decriptionTextView.text = foodsDecription
            ingradianTextView.text = foodIngradint
            detailPrice.text = foodPrice


            Glide.with(this@DetailsActivity).load(Uri.parse(foodImage)).into(DetailFoodImage)
        }


        //for back button
        binding.DetailBackButton.setOnClickListener {
            finish()
        }


    }

}