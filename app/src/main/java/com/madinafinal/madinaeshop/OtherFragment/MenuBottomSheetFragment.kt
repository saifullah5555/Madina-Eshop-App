package com.madinafinal.madinaeshop.OtherFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.madinafinal.madinaeshop.R
import com.madinafinal.madinaeshop.adapter.MenuAdapter
import com.madinafinal.madinaeshop.databinding.FragmentMenuBottomSheetBinding


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuBottomSheetBinding.inflate(inflater,container,false)
        binding.ButtonBackInMenu.setOnClickListener {
            dismiss()
        }
        val menuCartFoodName = listOf("জয়তুন ফল", "সরিষা মধু", "খলিসা ফুলের মধু", "সুস্বাধু ড্রাই ফুড",
            "জয়তুন ফল", "সরিষা মধু", "খলিসা ফুলের মধু", "সুস্বাধু ড্রাই ফুড")
        val menuCartItemPrice = listOf("৳ ২০০", "৳ ৫৯৯", "৳ ৮০০", "৳ ৫৫০",
            "৳ ২০০", "৳ ৫৯৯", "৳ ৮০০", "৳ ৫৫০")
        val menuCartImage = listOf(
            R.drawable.jaytun_banner2,
            R.drawable.sorisamodhu_banner,
            R.drawable.khalisa_banner,
            R.drawable.dryfood_banner,
            R.drawable.jaytun_banner2,
            R.drawable.sorisamodhu_banner,
            R.drawable.khalisa_banner,
            R.drawable.dryfood_banner,
        )

        val adapter = MenuAdapter(
            ArrayList(menuCartFoodName),
            ArrayList(menuCartItemPrice),
            ArrayList(menuCartImage))

        binding.allMenuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.allMenuRecyclerView.adapter = adapter




        return binding.root
    }

    companion object {

    }
}