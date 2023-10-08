package com.madinafinal.madinaeshop

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.madinafinal.madinaeshop.databinding.FragmentCongratBottomSheetBinding


class CongratBottomSheetFragment : BottomSheetDialogFragment(){
    private lateinit var binding: FragmentCongratBottomSheetBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCongratBottomSheetBinding.inflate(layoutInflater,container,false)

        binding.GoHome.setOnClickListener {
            val intent = Intent(requireContext(),MainActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    companion object {

    }
}