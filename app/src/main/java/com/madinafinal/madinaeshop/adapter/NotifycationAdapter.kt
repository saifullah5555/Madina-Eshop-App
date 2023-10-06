package com.madinafinal.madinaeshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.madinafinal.madinaeshop.databinding.FragmentNotifycationBottomBinding
import com.madinafinal.madinaeshop.databinding.NotifycationItemBinding

class NotifycationAdapter(private var notifycation: ArrayList<String>,
                          private var notifycationImage: ArrayList<Int>): RecyclerView.Adapter<NotifycationAdapter.NotifycationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifycationViewHolder {
        val binding=NotifycationItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotifycationViewHolder(binding)
    }



    override fun onBindViewHolder(holder: NotifycationViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int = notifycation.size
    inner class NotifycationViewHolder(private val binding: NotifycationItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                NotifycationTextView.text = notifycation[position]
                notiImageView.setImageResource(notifycationImage[position])
            }
        }

    }
}