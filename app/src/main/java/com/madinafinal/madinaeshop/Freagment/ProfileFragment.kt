package com.madinafinal.madinaeshop.Freagment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.madinafinal.madinaeshop.LoginActivity
import com.madinafinal.madinaeshop.databinding.FragmentProfileBinding
import com.madinafinal.madinaeshop.model.UserModel


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)

        binding.apply {
            profileName.isEnabled = false
            profileAddress.isEnabled = false
            profileEmail.isEnabled  = false
            profilePhone.isEnabled = false

        binding.editeProfilee.setOnClickListener {


                profileName.isEnabled = !profileName.isEnabled
                profileAddress.isEnabled = !profileAddress.isEnabled
                profileEmail.isEnabled = !profileEmail.isEnabled
                profilePhone.isEnabled = !profilePhone.isEnabled
            }
        }

        setUSerData()

        binding.profileSaveButton.setOnClickListener {
val name = binding.profileName.text.toString()
val email = binding.profileEmail.text.toString()
val address = binding.profileAddress.text.toString()
val phone = binding.profilePhone.text.toString()

            updateUserData(name,email,address,phone)

        }

        binding.logOutBtn.setOnClickListener {
            auth.signOut()
            val intent = Intent(context,LoginActivity::class.java)

            startActivity(intent)


         //   startActivity(Intent(this,SignUpActivity::class.java))
          //  finish()
        }

        // Inflate the layout for this fragment
        return binding.root

    }

    private fun updateUserData(name: String, email: String, address: String, phone: String) {
        val userId = auth.currentUser?.uid
        if (userId != null){
            val userReference = database.getReference().child("user").child(userId)
            val userData = hashMapOf(
                "name" to name,
                "address" to address,
                "email" to email,
                "phone" to phone,
                )
            userReference.setValue(userData).addOnSuccessListener {
                Toast.makeText(requireContext(), "আপনার তথ্যটি সফলভাবে সেভ করা হয়েছে🥰✨", Toast.LENGTH_SHORT).show()
            }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "আপনার তথ্যটি নবায়ন করা যায়নি😢", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun setUSerData() {
        val userId = auth.currentUser?.uid
        if (userId != null){
            val userRefrence = database.getReference("user").child(userId)
            userRefrence.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        val userProfile = snapshot.getValue(UserModel::class.java)
                        if (userId != null){
                            binding.profileName.setText(userProfile?.name)
                            binding.profileAddress.setText(userProfile?.address)
                            binding.profileEmail.setText(userProfile?.email)
                            binding.profilePhone.setText(userProfile?.phone)


                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }


    }


}