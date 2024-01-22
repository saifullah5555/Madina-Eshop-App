package com.madinafinal.madinaeshop

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.madinafinal.madinaeshop.databinding.ActivityDetails2Binding
import com.madinafinal.madinaeshop.model.CartItem

class DetailsActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityDetails2Binding
    private var foodName: String? = null
    private var foodImage: String? = null
    private var foodsDecription: String? = null
    private var foodIngradint: String? = null
    private var foodPrice: String? = null

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetails2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        // initialize Firebase auth
        auth = FirebaseAuth.getInstance()

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



            val imageList = ArrayList<SlideModel>()
            imageList.add(SlideModel(foodImage, ScaleTypes.FIT))
            imageList.add(SlideModel(R.drawable.jaytun_banner2, ScaleTypes.FIT))
            imageList.add(SlideModel(R.drawable.dryfood_banner, ScaleTypes.FIT))
            imageList.add(SlideModel(R.drawable.khalisa_banner, ScaleTypes.FIT))
            imageList.add(SlideModel(R.drawable.sorisamodhu_banner, ScaleTypes.FIT))


            val imageSlider = binding.imageSliderr
            imageSlider.setImageList(imageList)
            imageSlider.setImageList(imageList, ScaleTypes.FIT)




        }
        //for back button
        binding.DetailBackButton.setOnClickListener {
            finish()
        }

        binding.detailAddToCartButton.setOnClickListener {
            addItemToCart()
        }
        binding.detailByenowButton.setOnClickListener {

        }

    }



    private fun addItemToCart() {
        val database = FirebaseDatabase.getInstance().reference
        val userId = auth.currentUser?.uid ?: ""

        // Create a Cart Item Object
        val cartItem = CartItem(
            foodName.toString(),
            foodPrice.toString(), foodsDecription.toString(),
            foodImage.toString(), foodIngradint.toString(), 1
        )

        database.child("user").child(userId).child("CartItems").push().setValue(cartItem)
            .addOnSuccessListener {
                Toast.makeText(this, "Item added into cart successfully🥰", Toast.LENGTH_SHORT)
                    .show()
            }.addOnFailureListener {
                Toast.makeText(this, "Item not added 😥", Toast.LENGTH_SHORT).show()
            }
    }



}