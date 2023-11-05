package com.madinafinal.madinaeshop

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.madinafinal.madinaeshop.adapter.CartAdapter
import com.madinafinal.madinaeshop.databinding.FragmentCartBinding
import com.madinafinal.madinaeshop.model.CartItem


public open class BuyNowFun:Application() {
    private lateinit var binding: BuyNowFun
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var foodNames: MutableList<String>
    private lateinit var foodPrices: MutableList<String>
    private lateinit var foodDescriptions: MutableList<String>
    private lateinit var foodImagesUri: MutableList<String>
    private lateinit var foodingradians: MutableList<String>
    private lateinit var quantityss: MutableList<Int>
    private lateinit var cardAdapter: CartAdapter
    private lateinit var userId: String


    open fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate()

    }

  open fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Context {
        // Inflate the layout for this fragment
        binding = BuyNowFun()

        //initilize firebase
        auth = FirebaseAuth.getInstance()



        return applicationContext
    }

    open  fun getOrderItemDetail() {
        val orderIdReference: DatabaseReference =
            database.reference.child("user").child(userId).child("CartItems")
        val foodName = mutableListOf<String>()
        val foodPrice = mutableListOf<String>()
        val foodImage = mutableListOf<String>()
        val foodDescription = mutableListOf<String>()
        val foodIngredient = mutableListOf<String>()

        // get Item quantitis

        val foodQuantitties = cardAdapter.getUpdateItemQuantitys()

        orderIdReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSnapshot in snapshot.children) {
                    //get the cartItems to the respective List
                    val orderItem = foodSnapshot.getValue(CartItem::class.java)
                    // add items details to list
                    orderItem?.foodName?.let { foodName.add(it) }
                    orderItem?.foodPrice?.let { foodPrice.add(it) }
                    orderItem?.foodsDecription?.let { foodDescription.add(it) }
                    orderItem?.foodImage?.let { foodImage.add(it) }
                    orderItem?.foodIngradint?.let { foodIngredient.add(it) }


                }
                orderNow(
                    foodName,
                    foodPrice,
                    foodDescription,
                    foodImage,
                    foodIngredient,
                    foodQuantitties
                )
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    applicationContext,
                    "Order Making Failed. Please Try Again",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        })
    }

    open fun orderNow(
        foodName: MutableList<String>,
        foodPrice: MutableList<String>,
        foodDescription: MutableList<String>,
        foodImage: MutableList<String>,
        foodIngredient: MutableList<String>,
        foodQuantitties: MutableList<Int>
    ) {
        if ( foodName.isEmpty()|| foodImage.isEmpty()) {
            Toast.makeText(this, "not again buy", Toast.LENGTH_SHORT).show()

        }else{
            val intent = Intent(this, PayOutActivity::class.java)
            intent.putExtra("FoodItemName", foodName as ArrayList<String>)
            intent.putExtra("FoodItemPrice", foodPrice as ArrayList<String>)
            intent.putExtra("FoodItemDescription", foodDescription as ArrayList<String>)
            intent.putExtra("FoodItemImage", foodImage as ArrayList<String>)
            intent.putExtra("FoodItemIngredient", foodIngredient as ArrayList<String>)
            intent.putExtra("FoodItemQuantity", foodQuantitties as ArrayList<Int>)
            startActivity(intent)
        }

        }
    }



        // fatch from the database





