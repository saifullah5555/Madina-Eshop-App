package com.madinafinal.madinaeshop.Freagment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.madinafinal.madinaeshop.OtherFragment.MenuBottomSheetFragment
import com.madinafinal.madinaeshop.R
import com.madinafinal.madinaeshop.adapter.MenuAdapter
import com.madinafinal.madinaeshop.databinding.FragmentHomeBinding
import com.madinafinal.madinaeshop.model.MenuItemm
import android.view.View as View1


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var database: FirebaseDatabase
    private lateinit var menuItemsses: MutableList<MenuItemm>
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View1 {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        progressBar = binding.progressBarr
        binding.prtext.visibility = View.VISIBLE

        // লোডিং অ্যানিমেশন শুরু করুন
        progressBar.visibility = View.VISIBLE
        Toast.makeText(context, "আচ্ছালামু আলাইকুম😊🥰", Toast.LENGTH_SHORT).show()




        binding.ViewallmenuButton.setOnClickListener {
            val bottomSheetDialog = MenuBottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager, "test")
        }




        // retrieve and display popular menu Item
        retrieveAndDisplayPopularMenuItem()



        return binding.root



    }

    private fun retrieveAndDisplayPopularMenuItem() {
        //get reference to the database
        database= FirebaseDatabase.getInstance()
        val foodRef: DatabaseReference = database.reference.child("menu")
        menuItemsses = mutableListOf()

        // retrieve menu item form the database
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
             for (foodSnapshot in snapshot.children){
                 val menuItemm = foodSnapshot.getValue(MenuItemm::class.java)
                 menuItemm?.let {menuItemsses.add(it)}
             }
                // display random popular item
                randompopularItem()
            }

            private fun randompopularItem() {
                // create as shuffled list of menu item
                val index = menuItemsses.indices.toList().shuffled()
                val itemToShow = 20
                val subsetMenuItem = index.take(itemToShow).map { menuItemsses[it] }

                setPopularItemAdapter(subsetMenuItem)
            }

            private fun setPopularItemAdapter(subsetMenuItemm: List<MenuItemm>) {
                val adapter = MenuAdapter( subsetMenuItemm,requireContext())
                binding.PopularRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.PopularRecyclerView.adapter = adapter


                // ডেটা লোড হওয়ার পর লোডিং অ্যানিমেশন বন্ধ করুন
                progressBar.visibility = View.GONE
                binding.prtext.visibility = View.GONE

                // আপনার অ্যাপের UI-এর সাথে ডেটা প্রদর্শন করুন
                // ...
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun onViewCreated(view: View1, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.madina_banner1, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.jaytun_banner2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.dryfood_banner, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.khalisa_banner, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.sorisamodhu_banner, ScaleTypes.FIT))


        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)

    }
}
