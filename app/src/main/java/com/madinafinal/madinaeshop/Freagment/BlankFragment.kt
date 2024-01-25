package com.madinafinal.madinaeshop.Freagment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.madinafinal.madinaeshop.R
import com.madinafinal.madinaeshop.databinding.FragmentBlankBinding



class BlankFragment : androidx.fragment.app.Fragment() {


    private lateinit var binding: FragmentBlankBinding
    private lateinit var context: Context


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

        // to open navigation on click listener
        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }


        // set click listener in navigation menu item
        binding.NavigationView.setNavigationItemSelectedListener { valu ->



            binding.drawerLayout.close()

            // navigation er modde jei item tate click korbo seta bold hoe thakar jonno nicer code
            valu.isChecked = true

            when (valu.itemId) {

                R.id.nvhome -> {

                    findNavController().navigate(R.id. homeFragment)


                }

                R.id.nvUser -> {

                    findNavController().navigate(R.id. profileFragment)



                }

                R.id.nvNewItem -> {

                }

                R.id.nvLogOut -> {
                    findNavController().navigate(R.id.logOutFragment)
                }

                R.id.IslamicVideos ->{

                    findNavController().navigate(R.id.islamicVideoFragment)
                }






            }


            // is Checked likhle return korte hoi
            return@setNavigationItemSelectedListener true
        }

        // to access the hedar view in navigation view

        val HaderBinding = binding.NavigationView.getHeaderView(0)

        val userProfile: ImageView = HaderBinding.findViewById(R.id.userProfile)
        val title: TextView = HaderBinding.findViewById(R.id.Namee)

    title.setOnClickListener {
        startActivity(Intent(context,ProfileFragment::class.java))
    }


        return binding.root


    }




}