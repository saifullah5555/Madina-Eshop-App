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


        // val adapter =
        //   CartAdapter(
        //       ArrayList(CartFoodName),
        //      ArrayList(CartItemPrice),
        //     ArrayList(CartImage))

        binding.ProceedButton.setOnClickListener {
            val intent = Intent(requireContext(), PayOutActivity::class.java)
            startActivity(intent)
        }


        return binding.root
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
                val adapter = CartAdapter(
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
                binding.CartRecylerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "data not fetch", Toast.LENGTH_SHORT).show()
            }

        })
    }
}