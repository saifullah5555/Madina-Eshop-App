package com.madinafinal.madinaeshop

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.madinafinal.madinaeshop.databinding.FragmentIslamicVideoBinding



class IslamicVideoFragment : Fragment() {
    private lateinit var binding: FragmentIslamicVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIslamicVideoBinding.inflate(inflater, container, false)

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://youtu.be/iC6izVPJGLY?si=a-9lW-RK7cAKGK26")
        startActivity(intent)

        findNavController().navigate(R.id.blankFragment)

        return binding.root
    }


}