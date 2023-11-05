package com.madinafinal.madinaeshop.Freagment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.madinafinal.madinaeshop.adapter.MenuAdapter
import com.madinafinal.madinaeshop.databinding.FragmentSearchBinding
import com.madinafinal.madinaeshop.model.MenuItemm

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var adapter: MenuAdapter
    private lateinit var database: FirebaseDatabase
    private val originalMenuItem = mutableListOf<MenuItemm>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        // retrieve menuItem from database
        retrieveMenuItem()

        //setup for Search View

        setupSearchView()




        return binding.root
    }

    private fun retrieveMenuItem() {
        // get database firebase
        database = FirebaseDatabase.getInstance()
        // refrence to the menu node
        val foodReference: DatabaseReference = database.reference.child("menu")
        foodReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSnapshot in snapshot.children) {
                    val menuItem = foodSnapshot.getValue(MenuItemm::class.java)
                    menuItem?.let {
                        originalMenuItem.add(it)
                    }
                }
                showAllMenu()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun showAllMenu() {
        val filteredMenuItem = ArrayList(originalMenuItem)
        setAdepter(filteredMenuItem)
    }

    private fun setAdepter(filteredMenuItem: List<MenuItemm>) {
        adapter = MenuAdapter(filteredMenuItem, requireContext())
        binding.allMenuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.allMenuRecyclerView.adapter = adapter
    }


    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
        val fielterMenuItam = originalMenuItem.filter {
            it.foodName?.contains(query, ignoreCase = true) == true
        }
        setAdepter(fielterMenuItam)
    }

    companion object {

    }

}



