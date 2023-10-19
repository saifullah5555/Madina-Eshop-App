package com.madinafinal.madinaeshop.adapter


import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.madinafinal.madinaeshop.databinding.CartItemBinding


class CartAdapter(
    private val context: Context,
    private val cartItem: MutableList<String>,
    private val cartItemPrice: MutableList<String>,
    private var cartDecription: MutableList<String>,
    private val cartImagess: MutableList<String>,
    private var cartIngredient: MutableList<String>,
    private val cartQuantity: MutableList<Int>
) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    // Instance Firebase
    private val auth = FirebaseAuth.getInstance()
init {
    val database = FirebaseDatabase.getInstance()
    val userId = auth.currentUser?.uid?:""
    val cartItemNumber = cartItem.size

    itemQuantiti = IntArray(cartItemNumber){1}
    cartItemRefrence = database.reference.child("user").child(userId).child("CartItems")
}
    companion object{


        private var itemQuantiti:IntArray= intArrayOf()
        private lateinit var cartItemRefrence: DatabaseReference
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = cartItem.size
    // get updated Quantity
    fun getUpdateItemQuantitys(): MutableList<Int> {
        val iteQuantity = mutableListOf<Int>()
        iteQuantity.addAll(cartQuantity)
        return iteQuantity
    }


    inner class CartViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantiti[position]
                CartFoodName.text = cartItem[position]
                CartItemPrice.text = cartItemPrice[position]

                cartItemQuantity.text = quantity.toString()
                // load using glide for image
                val uriString = cartImagess[position]
                val uri = Uri.parse(uriString)
              Glide.with(context).load(uri).into(CartImage)


                CartMinasButton.setOnClickListener {
                    deceaseQuantity(position)
                }

                CartPlusButton.setOnClickListener {
                    increaseQuantity(position)
                }
                CartDeleteButton.setOnClickListener {
                    val itemPosition = adapterPosition
                    if (itemPosition != RecyclerView.NO_POSITION) {
                        deleteItem(itemPosition)
                    }

                }
            }

        }


        private fun increaseQuantity(position: Int) {
            if (itemQuantiti[position] < 10) {
                itemQuantiti[position]++
                cartQuantity[position] = itemQuantiti[position]
                binding.cartItemQuantity.text = itemQuantiti[position].toString()

            }

        }

        private fun deceaseQuantity(position: Int) {
            if (itemQuantiti[position] > 1) {
                itemQuantiti[position]--
                cartQuantity[position] = itemQuantiti[position]
                binding.cartItemQuantity.text = itemQuantiti[position].toString()
            }
        }

        private fun deleteItem(position: Int) {
           val positionRetrieve = position
            getUniqKeyAtPosition(positionRetrieve){uniquekey ->
if (uniquekey != null){
    removeItem(position,uniquekey)
}
                cartQuantity[position] = itemQuantiti[position]
            }


        }

        private fun removeItem(position: Int, uniquekey: String) {
            if (uniquekey != null){
                cartItemRefrence.child(uniquekey).removeValue().addOnSuccessListener {
                    cartItem.removeAt(position)
                    cartImagess.removeAt(position)
                    cartDecription.removeAt(position)
                    cartItemPrice.removeAt(position)
                    cartIngredient.removeAt(position)
                    cartQuantity.removeAt(position)
                    Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show()
                    // update Item Quantity
                    itemQuantiti = itemQuantiti.filterIndexed{index, i -> index!= position }.toIntArray()
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position,cartItem.size)
                }.addOnFailureListener {
                    Toast.makeText(context, "Faild to Delete", Toast.LENGTH_SHORT).show()
                }
            }
        }

        private fun getUniqKeyAtPosition(positionRetrieve: Int , onComplete:(String?)-> Unit) {
            cartItemRefrence.addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var uniqueKey: String?=null
                    //loop for snapshot childrren
                    snapshot.children.forEachIndexed { index, dataSnapshot ->
                        if ( index == positionRetrieve ){
                            uniqueKey= dataSnapshot.key
                            return@forEachIndexed
                        }
                    }
                    onComplete(uniqueKey)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}