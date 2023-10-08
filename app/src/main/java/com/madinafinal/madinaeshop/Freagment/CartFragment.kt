package com.madinafinal.madinaeshop.Freagment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.madinafinal.madinaeshop.PayOutActivity
import com.madinafinal.madinaeshop.R
import com.madinafinal.madinaeshop.adapter.CartAdapter
import com.madinafinal.madinaeshop.databinding.ActivityPayOutBinding
import com.madinafinal.madinaeshop.databinding.FragmentCartBinding



class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)

        val CartFoodName = listOf("জয়তুন ফল", "সরিষা মধু", "খলিসা ফুলের মধু", "সুস্বাধু ড্রাই ফুড")
        val CartItemPrice = listOf("৳ ২০০", "৳ ৫৯৯", "৳ ৮০০", "৳ ৫৫০")
        val CartImage = listOf(
            R.drawable.jaytun_banner2,
            R.drawable.sorisamodhu_banner,
            R.drawable.khalisa_banner,
            R.drawable.dryfood_banner
        )

        val adapter =
            CartAdapter(
                ArrayList(CartFoodName),
                ArrayList(CartItemPrice),
                ArrayList(CartImage))
        binding.CartRecylerView.layoutManager = LinearLayoutManager(requireContext())
        binding.CartRecylerView.adapter = adapter
        binding.ProceedButton.setOnClickListener {
            val intent = Intent(requireContext(),PayOutActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }
}