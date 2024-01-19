package com.madinafinal.madinaeshop.Freagment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.madinafinal.madinaeshop.databinding.FragmentBlankBinding


class BlankFragment : androidx.fragment.app.Fragment() {
    private lateinit var binding: FragmentBlankBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlankBinding.inflate(inflater, container, false)

        //er modde code koro



        return binding.root
    }

}