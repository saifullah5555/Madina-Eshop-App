package com.madinafinal.madinaeshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.madinafinal.madinaeshop.databinding.ByeAgainItemBinding

class ByeAgainAdapter(private val ByeAgainFoodNamee: List<String>,
                      private val ByeAgainFoodPricee: List<String>,
                      private val ByeAgainFoodImagee: List<Int>
): RecyclerView
.Adapter<ByeAgainAdapter.ByeAgainViewHolder>() {

    override fun onBindViewHolder(holder: ByeAgainViewHolder, position: Int) {
        holder.bind(ByeAgainFoodNamee[position],ByeAgainFoodPricee[position],ByeAgainFoodImagee[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ByeAgainViewHolder {
        val binding = ByeAgainItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ByeAgainViewHolder(binding)
    }


    override fun getItemCount(): Int = ByeAgainFoodNamee.size
    class ByeAgainViewHolder(private val binding: ByeAgainItemBinding):RecyclerView.ViewHolder
        (binding.root) {
        fun bind(foodName: String, foodPrice: String, foodImage: Int) {
            binding.byeAgainFoodName.text= foodName
            binding.byeAgainFoodPrice.text= foodPrice
            binding.byeAgainFoodImage.setImageResource(foodImage)
        }

    }


}