package com.madinafinal.madinaeshop.Freagment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.madinafinal.madinaeshop.PayOutActivity
import com.madinafinal.madinaeshop.adapter.CartAdapter
import com.madinafinal.madinaeshop.databinding.FragmentCartBinding
import com.madinafinal.madinaeshop.model.CartItem


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)

        //initilize firebase
        auth = FirebaseAuth.getInstance()
        retrieveCartItem()

        binding.ProceedButton.setOnClickListener {
            // get order Item Details before processioning to check out
            getOrderItemDetail()
        }
        return binding.root
    }

    private fun getOrderItemDetail() {
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
                    requireContext(),
                    "Order Making Failed. Please Try Again",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        })
    }

    private fun orderNow(
        foodName: MutableList<String>,
        foodPrice: MutableList<String>,
        foodDescription: MutableList<String>,
        foodImage: MutableList<String>,
        foodIngredient: MutableList<String>,
        foodQuantitties: MutableList<Int>
    ) {
        if (isAdded && context != null) {
            val intent = Intent(requireContext(), PayOutActivity::class.java)
            intent.putExtra("FoodItemName", foodName as ArrayList<String>)
            intent.putExtra("FoodItemPrice", foodPrice as ArrayList<String>)
            intent.putExtra("FoodItemDescription", foodDescription as ArrayList<String>)
            intent.putExtra("FoodItemImage", foodImage as ArrayList<String>)
            intent.putExtra("FoodItemIngredient", foodIngredient as ArrayList<String>)
            intent.putExtra("FoodItemQuantity", foodQuantitties as ArrayList<Int>)
            startActivity(intent)
        }
    }

    private fun retrieveCartItem() {
        // database refrence to the firebase
        database = FirebaseDatabase.getInstance()
        userId = auth.currentUser?.uid ?: ""
        val foodRefrence: DatabaseReference =
            database.reference.child("user").child(userId).child("CartItems")
        //list to store cart Items
        foodNames = mutableListOf()
        foodPrices = mutableListOf()
        foodDescriptions = mutableListOf()
        foodingradians = mutableListOf()
        foodImagesUri = mutableListOf()
        quantityss = mutableListOf()

        // fatch from the database
        foodRefrence.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSnapshot in snapshot.children) {
                    // get the cartIem object from child node
                    val cartItems = foodSnapshot.getValue(CartItem::class.java)
                    //add cart Items to the list
                    cartItems?.foodName?.let { foodNames.add(it) }
                    cartItems?.foodPrice?.let { foodPrices.add(it) }
                    cartItems?.foodImage?.let { foodImagesUri.add(it) }
                    cartItems?.foodIngradint?.let { foodingradians.add(it) }
                    cartItems?.foodsDecription?.let { foodDescriptions.add(it) }
                    cartItems?.foodQuantity?.let { quantityss.add(it) }
                }
                setAdapter()
            }

            private fun setAdapter() {
                cardAdapter = CartAdapter(
                    requireContext(),
                    foodNames,
                    foodPrices,
                    foodDescriptions,
                    foodImagesUri,
                    foodingradians,
                    quantityss
                )
                binding.CartRecylerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.CartRecylerView.adapter = cardAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "data not fetch", Toast.LENGTH_SHORT).show()
            }

        })
    }
}