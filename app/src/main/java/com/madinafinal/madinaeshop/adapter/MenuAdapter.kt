package com.madinafinal.madinaeshop.adapter


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.madinafinal.madinaeshop.DetailsActivity2
import com.madinafinal.madinaeshop.databinding.MenuItemBinding
import com.madinafinal.madinaeshop.model.MenuItemm

class MenuAdapter(
    private val menuItemm: List<MenuItemm>,
    private val requireContext: Context
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuItemm.size

    inner class MenuViewHolder(private val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    openDetailActivity(position)
                }
            }
        }

        private fun openDetailActivity(position: Int) {
            val menuItem = menuItemm[position]

            // a intent to open detail activity and pass data
            val intent = Intent(requireContext, DetailsActivity2::class.java).apply {
                putExtra("menufoodName", menuItem.foodName)
                putExtra("menuimage", menuItem.foodImage)
                putExtra("decriptionTextView", menuItem.foodsDecription)
                putExtra("ingradianTextView", menuItem.foodIngradint)
                putExtra("menuitemprice", menuItem.foodPrice)
            }
            //start the detail activity
            requireContext.startActivity(intent)
        }
 // set data in to recycler view ,image, name, price
        fun bind(position: Int) {
            val menuItem = menuItemm[position]
            binding.apply {
                menufoodName.text = menuItem.foodName
                menuitemprice.text = menuItem.foodPrice
                val uri = Uri.parse(menuItem.foodImage)
                Glide.with(requireContext).load(uri).into(menuimage)


            }
        }

    }

}


