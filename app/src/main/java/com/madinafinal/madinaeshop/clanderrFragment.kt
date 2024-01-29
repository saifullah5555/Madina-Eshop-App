package com.madinafinal.madinaeshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.madinafinal.madinaeshop.databinding.FragmentClanderrBinding

class clanderrFragment : Fragment() {
    private lateinit var binding: FragmentClanderrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentClanderrBinding.inflate(inflater,container,false)


        binding.ProfilerBackButton.setOnClickListener {
            findNavController().navigate(R.id.blankFragment)
        }


      return binding.root
    }


}