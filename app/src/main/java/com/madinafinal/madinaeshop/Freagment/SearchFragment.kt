package com.madinafinal.madinaeshop.Freagment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.madinafinal.madinaeshop.R
import com.madinafinal.madinaeshop.adapter.MenuAdapter
import com.madinafinal.madinaeshop.databinding.FragmentSearchBinding
import com.madinafinal.madinaeshop.databinding.MenuItemBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var adapter: MenuAdapter
    private val orignalMenuFoodName = listOf("জয়তুন ফল", "সরিষা মধু", "খলিসা ফুলের মধু", "সুস্বাধু ড্রাই ফুড",
        "জয়তুন ফল", "সরিষা মধু", "খলিসা ফুলের মধু", "সুস্বাধু ড্রাই ফুড")

      private val orignalmenuItemPrice = listOf("৳ ২০০", "৳ ৫৯৯", "৳ ৮০০", "৳ ৫৫০",
        "৳ ২০০", "৳ ৫৯৯", "৳ ৮০০", "৳ ৫৫০")

        private val orignalMenuCartImage = listOf(
        R.drawable.jaytun_banner2,
        R.drawable.sorisamodhu_banner,
        R.drawable.khalisa_banner,
        R.drawable.dryfood_banner,
        R.drawable.jaytun_banner2,
        R.drawable.sorisamodhu_banner,
        R.drawable.khalisa_banner,
        R.drawable.dryfood_banner,)


       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       }
       private val filterMenuItemName = mutableListOf<String>()
       private val filterMenuItemPrice = mutableListOf<String>()
       private val filterMenuImage = mutableListOf<Int>()
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container,false)

        adapter = MenuAdapter(filterMenuItemName,filterMenuItemPrice,filterMenuImage)
        binding.allMenuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.allMenuRecyclerView.adapter = adapter

            //setup for Search View
            setupSearchView()
            //show all menu items
            showAllMane()


        return binding.root
        }

    private fun showAllMane() {
        filterMenuItemName.clear()
        filterMenuItemPrice.clear()
        filterMenuImage.clear()

        filterMenuItemName.addAll(orignalMenuFoodName)
        filterMenuItemPrice.addAll(orignalmenuItemPrice)
        filterMenuImage.addAll(orignalMenuCartImage)
        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true

            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText)
                return true
            }
        })
    }

    private fun filterMenuItems(query: String) {
      filterMenuItemName.clear()
      filterMenuItemPrice.clear()
      filterMenuImage.clear()

        orignalMenuFoodName.forEachIndexed { index, foodName ->
            if (foodName.contains(query,ignoreCase = true)){
                filterMenuItemName.add(foodName)
                filterMenuItemPrice.add(orignalmenuItemPrice[index])
                filterMenuImage.add(orignalMenuCartImage[index])
            }
        }
        adapter.notifyDataSetChanged()
    }

    companion object {

        }
}
