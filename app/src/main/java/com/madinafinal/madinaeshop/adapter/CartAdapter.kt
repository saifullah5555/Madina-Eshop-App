package com.madinafinal.madinaeshop.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.madinafinal.madinaeshop.databinding.CartItemBinding


class CartAdapter (private val cartItem:MutableList<String>,
                   private val cartItemPrice:MutableList<String>,
                   private val cartImage:MutableList<Int>):
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

private val itemQuantity = IntArray (cartItem.size) { 1 }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int = cartItem.size

    inner class CartViewHolder(private val binding:CartItemBinding):RecyclerView.ViewHolder(binding.root) {


        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantity[position]
                CartFoodName.text = cartItem[position]
                CartItemPrice.text = cartItemPrice[position]
                CartImage.setImageResource(cartImage[position])
                cartItemQuantity.text = quantity.toString()


                CartMinasButton.setOnClickListener {
                 deceaseQuantity(position)
                }

                CartPlusButton.setOnClickListener {
                increaseQuantity(position)
                }
                CartDeleteButton.setOnClickListener {
                val itemPosition= adapterPosition
                    if(itemPosition != RecyclerView.NO_POSITION){
                        deleteItem(itemPosition)

                    }

                }
            }

        }
        private fun increaseQuantity(position: Int) {
            if (itemQuantity[position]>10){
                itemQuantity[position] ++
                binding.cartItemQuantity.text = itemQuantity[position].toString()
            }

        }
        private  fun deceaseQuantity(position: Int) {
            if (itemQuantity[position]>1){
                itemQuantity[position]--
                binding.cartItemQuantity.text = itemQuantity[position].toString()
            }
        }
       private fun deleteItem(position: Int){
            cartItem.removeAt(position)
            cartImage.removeAt(position)
            cartItemPrice.removeAt(position)

            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItem.size)


        }
      }
    }