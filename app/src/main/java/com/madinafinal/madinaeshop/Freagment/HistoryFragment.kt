package com.madinafinal.madinaeshop.Freagment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.madinafinal.madinaeshop.R
import com.madinafinal.madinaeshop.adapter.ByeAgainAdapter
import com.madinafinal.madinaeshop.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var byeAgainAdapter: ByeAgainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHistoryBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        setupRecyclerView()
        return binding.root
    }
    private fun setupRecyclerView(){
         val byeagainFoodName = listOf("জয়তুন ফল", "সরিষা মধু", "খলিসা ফুলের মধু", "সুস্বাধু ড্রাই ফুড",
            "জয়তুন ফল", "সরিষা মধু", "খলিসা ফুলের মধু", "সুস্বাধু ড্রাই ফুড")

        val byeagainFoodPrice = listOf("৳ ২০০", "৳ ৫৯৯", "৳ ৮০০", "৳ ৫৫০",
            "৳ ২০০", "৳ ৫৯৯", "৳ ৮০০", "৳ ৫৫০")

         val byeagainFoodImage = listOf(
            R.drawable.jaytun_banner2,
            R.drawable.sorisamodhu_banner,
            R.drawable.khalisa_banner,
            R.drawable.dryfood_banner,
            R.drawable.jaytun_banner2,
            R.drawable.sorisamodhu_banner,
            R.drawable.khalisa_banner,
            R.drawable.dryfood_banner,)
        byeAgainAdapter = ByeAgainAdapter(byeagainFoodName,byeagainFoodPrice,byeagainFoodImage)
        binding.ByeAgainRecyclerView.adapter = byeAgainAdapter
        binding.ByeAgainRecyclerView.layoutManager= LinearLayoutManager(requireContext())
    }

    companion object {


    }
}