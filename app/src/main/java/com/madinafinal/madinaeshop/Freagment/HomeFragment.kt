package com.madinafinal.madinaeshop.Freagment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.madinafinal.madinaeshop.R
import com.madinafinal.madinaeshop.databinding.FragmentHomeBinding
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.madina_banner1, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.jaytun_banner2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.dryfood_banner, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.khalisa_banner, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.sorisamodhu_banner, ScaleTypes.FIT))


        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
    }
}