package com.madinafinal.madinaeshop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.madinafinal.madinaeshop.databinding.FragmentLogOutBinding


class LogOutFragment : Fragment() {
    private val auth = FirebaseAuth.getInstance()
    private lateinit var binding: FragmentLogOutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogOutBinding.inflate(inflater, container, false)

        auth.signOut()
        val intent = Intent(context, LoginActivity::class.java)

        startActivity(intent)



        return binding.root
    }


}