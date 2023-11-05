package com.madinafinal.madinaeshop.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.madinafinal.madinaeshop.databinding.ByeAgainItemBinding

class ByeAgainAdapter(
    private val ByeAgainFoodNamee: MutableList<String>,
    private val ByeAgainFoodPricee: MutableList<String>,
    private val ByeAgainFoodImagee: MutableList<String>,
    private var requireContext: Context) : RecyclerView.Adapter<ByeAgainAdapter.ByeAgainViewHolder>() {
    override fun onBindViewHolder(holder: ByeAgainViewHolder, position: Int) {

        holder.bind(ByeAgainFoodNamee[position],
            ByeAgainFoodPricee[position],
            ByeAgainFoodImagee[position])
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ByeAgainViewHolder {
        val binding =
            ByeAgainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ByeAgainViewHolder(binding)
    }
    override fun getItemCount(): Int = ByeAgainFoodNamee.size
    inner class ByeAgainViewHolder(private val binding: ByeAgainItemBinding) :
        RecyclerView.ViewHolder
            (binding.root) {
        fun bind(foodName: String, foodPrice: String, foodImage: String) {
            binding.byeAgainFoodName.text = foodName
            binding.byeAgainFoodPrice.text = foodPrice
            val uriString = foodImage
            val uri = Uri.parse(uriString)
            Glide.with(requireContext).load(uri).into(binding.byeAgainFoodImage)
        }
    }
}