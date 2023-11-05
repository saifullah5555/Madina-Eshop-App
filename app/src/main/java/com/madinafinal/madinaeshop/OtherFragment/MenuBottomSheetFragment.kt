package com.madinafinal.madinaeshop.OtherFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.madinafinal.madinaeshop.adapter.MenuAdapter
import com.madinafinal.madinaeshop.databinding.FragmentMenuBottomSheetBinding
import com.madinafinal.madinaeshop.model.MenuItemm


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding


    private lateinit var database: FirebaseDatabase
    private lateinit var menuItemms: MutableList<com.madinafinal.madinaeshop.model.MenuItemm>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)
        binding.ButtonBackInMenu.setOnClickListener {
            dismiss()
        }
        retrieveMenuItem()
        return binding.root
    }

    private fun retrieveMenuItem() {
// problem this
        database = FirebaseDatabase.getInstance()
        val foodRef: DatabaseReference = database.reference.child("menu")
        menuItemms = mutableListOf()
        //fetch from database
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSnapshot in snapshot.children){
                    val manuItem = foodSnapshot.getValue(MenuItemm::class.java)
                    manuItem?.let {menuItemms.add(it) }
                }
                // once data received , set to adapter
                setAdapter()
            }



            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    private fun setAdapter() {
        val  adapter = MenuAdapter(menuItemms,requireContext())
        binding.allMenuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.allMenuRecyclerView.adapter = adapter
    }


companion object {

}


}