package com.madinafinal.madinaeshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.madinafinal.madinaeshop.databinding.ActivityPayOutBinding
import com.madinafinal.madinaeshop.model.OrderDetails

class PayOutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPayOutBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var name: String
    private lateinit var address: String
    private lateinit var phone: String
    private lateinit var price: String
    private lateinit var totalAmount: String
    private lateinit var foodItemName: ArrayList<String>
    private lateinit var foodItemPrice: ArrayList<String>
    private lateinit var foodItemDescription: ArrayList<String>
    private lateinit var foodItemImage: ArrayList<String>
    private lateinit var foodItemIngredient: ArrayList<String>
    private lateinit var foodItemQuantity: ArrayList<Int>
    private lateinit var databaseRefrence: DatabaseReference
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPayOutBinding.inflate(layoutInflater)

        setContentView(binding.root)
        // Initialize firebase and User Details
        auth = FirebaseAuth.getInstance()
        databaseRefrence = FirebaseDatabase.getInstance().reference

        //set user data
        setUserData()

        // get user data from firebase
        val intent = intent
        foodItemName = intent.getStringArrayListExtra("FoodItemName") as ArrayList<String>
        foodItemPrice = intent.getStringArrayListExtra("FoodItemPrice") as ArrayList<String>
        foodItemDescription =
            intent.getStringArrayListExtra("FoodItemDescription") as ArrayList<String>
        foodItemImage = intent.getStringArrayListExtra("FoodItemImage") as ArrayList<String>
        foodItemIngredient =
            intent.getStringArrayListExtra("FoodItemIngredient") as ArrayList<String>
        foodItemQuantity = intent.getIntegerArrayListExtra("FoodItemQuantity") as ArrayList<Int>

        totalAmount = calculateTotalPrice().toString() + "৳"

        binding.totalAmount.isEnabled = false
        binding.totalAmount.text = totalAmount




        binding.payoutBackBtn.setOnClickListener {
            finish()
        }

        binding.plaseMyOrderBtn.setOnClickListener {
            // text view er lekha gulo admin app e neoar jonno ei code
            name = binding.PayName.text.toString().trim()
            address = binding.PayAddress.text.toString().trim()
            phone = binding.PayPhone.text.toString().trim()
            price = binding.totalAmount.text.toString().trim()
            if (name.isBlank() || address.isBlank() || phone.isBlank() || price.isBlank()) {
                Toast.makeText(this, "অনুগ্রহ পূর্বক সমস্ত তথ্য গুলি লিখুন 🥰✨", Toast.LENGTH_SHORT)
                    .show()
            } else {
                placeOrder()
            }


        }
    }

    private fun placeOrder() {
        userId = auth.currentUser?.uid ?: ""
        val time = System.currentTimeMillis()
        val itemPushKey = databaseRefrence.child("OrderDetails").push().key
        val orderDetails = OrderDetails(
            userId,
            name,
            address,
            phone,
            price,
            foodItemName,
            foodItemPrice,
            foodItemImage,
            foodItemQuantity,
            time,
            itemPushKey,
            false,
            false
        )
        val orderReference = databaseRefrence.child("OrderDetails").child(itemPushKey!!)
        orderReference.setValue(orderDetails).addOnSuccessListener {
            // button e click korar por jekono ekta fragment open korar jonno ei code
            val BottomSheetDyalog = CongratBottomSheetFragment()
            BottomSheetDyalog.show(supportFragmentManager, "test")
            removeItemFromCrat()
            addOrderToHestory(orderDetails)

        }.addOnFailureListener {
            Toast.makeText(this, "আবার চেষ্টা করুন", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addOrderToHestory(orderDetails: OrderDetails) {
        databaseRefrence.child("user").child(userId).child("BuyHistory")
            .child(orderDetails.itemPushKey!!)
            .setValue(orderDetails).addOnSuccessListener {

            }
    }

    private fun removeItemFromCrat() {
        val cartItemsReference = databaseRefrence.child("user").child(userId).child("CartItems")
        cartItemsReference.removeValue()
    }

    private fun calculateTotalPrice(): Int {
        var totalAmount = 0
        for (i in 0 until foodItemPrice.size) {
            var price = foodItemPrice[i]
            val lastTakaIcon = price.last()
            val priceIntValue = if (lastTakaIcon == '৳') {
                price.dropLast(1).toInt()
            } else {
                price.toInt()
            }
            val quantity = foodItemQuantity[i]
            totalAmount += priceIntValue * quantity


        }
        return totalAmount
    }

    private fun setUserData() {
        val user = auth.currentUser

        if (user != null) {
            val userId = user.uid
            val userReference = databaseRefrence.child("user").child(userId)
            userReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val names = snapshot.child("name").getValue(String::class.java) ?: ""
                        val addresses = snapshot.child("address").getValue(String::class.java) ?: ""
                        val phones = snapshot.child("phone").getValue(String::class.java) ?: ""
                        binding.apply {
                            PayName.setText(names)
                            PayAddress.setText(addresses)
                            PayPhone.setText(phones)

                        }
                    } else {

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }


    }
}