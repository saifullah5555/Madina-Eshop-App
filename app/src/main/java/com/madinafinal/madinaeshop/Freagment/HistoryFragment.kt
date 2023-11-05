package com.madinafinal.madinaeshop.Freagment

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.madinafinal.madinaeshop.recentOrderItems
import com.madinafinal.madinaeshop.adapter.ByeAgainAdapter
import com.madinafinal.madinaeshop.databinding.FragmentHistoryBinding
import com.madinafinal.madinaeshop.model.OrderDetails

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var byeAgainAdapter: ByeAgainAdapter
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var userId: String
    private var listOfOrderItem: ArrayList<OrderDetails> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {            //ekhane kono somossa ase naki?
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(                                       //ekhane kono somossa ase naki?
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        // retrieve and display the User Order History
        retrieveBuyHistory()

        binding.recentBuyItemt.setOnClickListener {
            seeItemRecentBuy()
        }

        binding.ReceivedButton.setOnClickListener {
            updateOrderStatus()
        }


        return binding.root
    }

    private fun updateOrderStatus() {
        val itemPushKey = listOfOrderItem[0].itemPushKey
        val completeOrderReference = database.reference.child("CompletedOrder").child(itemPushKey!!)
        completeOrderReference.child("paymentReceived").setValue(true)
        Toast.makeText(context, "আমাদের থেকে ক্রয় করার জন্য আপনাকে জানাই আন্তরিক মোবারক বাদ🥰✨", Toast.LENGTH_SHORT).show()
    }

    private fun seeItemRecentBuy() {
        listOfOrderItem.firstOrNull()?.let { recentBuy ->
            val intent = Intent(requireContext(), recentOrderItems::class.java)
            intent.putExtra("RecentBuyOrderItem", listOfOrderItem)
            startActivity(intent)
        }
    }

    private fun retrieveBuyHistory() {
        binding.recentBuyItemt.visibility = View.INVISIBLE
        userId = auth.currentUser?.uid ?: ""

        val buyItemReference: DatabaseReference =
            database.reference.child("user").child(userId).child("BuyHistory")
        val shortQuire = buyItemReference.orderByChild("currentTime")

        shortQuire.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (byeSnapShot in snapshot.children) {
                    val buyHistoryItem = byeSnapShot.getValue(OrderDetails::class.java)
                    buyHistoryItem?.let {
                        listOfOrderItem.add(it)
                    }
                }
                listOfOrderItem.reverse()
                if (listOfOrderItem.isNotEmpty()) {
                    // display the most recent order details
                    setDataInRecentBuyItem()
                    // setup the recyclerview with previous order
                    setPreviousBuyItemRecyclerView()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    // function to display the most recent order details
    private fun setDataInRecentBuyItem() {
        binding.recentBuyItemt.visibility = View.VISIBLE
        val recentOrderItem = listOfOrderItem.firstOrNull()
        recentOrderItem?.let {
            with(binding) {
                byeAgainFoodName.text = it.foodNames?.firstOrNull() ?:""
                byeAgainFoodPrice.text = it.foodPrices?.firstOrNull() ?: ""
                val image = it.foodImages?.firstOrNull() ?: ""
                val uri = Uri.parse(image)
                Glide.with(requireContext()).load(uri).into(byeAgainFoodImage)

                val isOrderIsAccepted = listOfOrderItem[0].orderAccepted
                Log.d("TAG", "setDataInRecentBuyItem: $isOrderIsAccepted")
                if (isOrderIsAccepted){
                    orderStutus.background.setTint(Color.GREEN)
                    ReceivedButton.visibility = View.VISIBLE
                }
            }
        }
    }

    //function to setup the recyclerview with previous order
    private fun setPreviousBuyItemRecyclerView() {
        val byeagainFoodName = mutableListOf<String>()
        val byeagainFoodPrice = mutableListOf<String>()
        val byeagainFoodImage = mutableListOf<String>()

        for (i in 1 until listOfOrderItem.size) {
            listOfOrderItem[i].foodNames?.firstOrNull()?.let {
                byeagainFoodName.add(it)
                listOfOrderItem[i].foodPrices?.firstOrNull()?.let {
                    byeagainFoodPrice.add(it)
                    listOfOrderItem[i].foodImages?.firstOrNull()?.let {
                        byeagainFoodImage.add(it)
                    }
                }
                val rv = binding.ByeAgainRecyclerView
                rv.layoutManager = LinearLayoutManager(requireContext())
                byeAgainAdapter = ByeAgainAdapter(
                    byeagainFoodName,
                    byeagainFoodPrice,
                    byeagainFoodImage,
                    requireContext()
                )
                rv.adapter = byeAgainAdapter
            }
        }
    }
}
