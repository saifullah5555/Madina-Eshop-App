package com.madinafinal.madinaeshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.madinafinal.madinaeshop.adapter.RecentBuyAdapter
import com.madinafinal.madinaeshop.databinding.ActivityRecentOrderItemsBinding
import com.madinafinal.madinaeshop.model.OrderDetails

class recentOrderItems : AppCompatActivity() {
    private val binding: ActivityRecentOrderItemsBinding by lazy {
        ActivityRecentOrderItemsBinding.inflate(layoutInflater)
    }
    private lateinit var allFoodNames: ArrayList<String>
    private lateinit var allFoodImages: ArrayList<String>
    private lateinit var allFoodPrices: ArrayList<String>
    private lateinit var allFoodQuantities: ArrayList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish() }
        val recentOrderItems =
            intent.getSerializableExtra("RecentBuyOrderItem") as ArrayList<OrderDetails>
        recentOrderItems ?.let { OrderDetails ->
            if (OrderDetails.isNotEmpty()) {
                val recentOrderItem = OrderDetails[0]
                allFoodNames = recentOrderItem.foodNames as ArrayList<String>
                allFoodImages = recentOrderItem.foodImages as ArrayList<String>
                allFoodPrices = recentOrderItem.foodPrices as ArrayList<String>
                allFoodQuantities = recentOrderItem.foodQuantities as ArrayList<Int>

            }

        }
        setAdapter()
    }

    private fun setAdapter() {
        val rv = binding.recycleViewItem
        rv.layoutManager = LinearLayoutManager(this)
        val adapter =
            RecentBuyAdapter(this, allFoodNames, allFoodImages, allFoodPrices, allFoodQuantities)
        rv.adapter = adapter
    }
}