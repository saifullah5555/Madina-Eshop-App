package com.madinafinal.madinaeshop.OtherFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.madinafinal.madinaeshop.R
import com.madinafinal.madinaeshop.adapter.NotifycationAdapter
import com.madinafinal.madinaeshop.databinding.FragmentNotifycationBottomBinding

class Notifycation_Bottom_Fragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNotifycationBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentNotifycationBottomBinding.inflate(layoutInflater, container,false)
        val notification= listOf("Your order has been Canceled Successfully","Order has been taken by the driver",
            "Congrats Your Order Placed")
        val notificationImages = listOf(R.drawable.sademoji,
                                          R.drawable.truck,
                                           R.drawable.congrats)

        val adapter= NotifycationAdapter(
            ArrayList(notification),
            ArrayList(notificationImages)
        )
        binding.notificationRecView.layoutManager= LinearLayoutManager(requireContext())
        binding.notificationRecView.adapter= adapter
        return binding.root
    }


    companion object {

    }
}