package com.madinafinal.madinaeshop.Freagment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.madinafinal.madinaeshop.OtherFragment.MenuBottomSheetFragment
import com.madinafinal.madinaeshop.R
import com.madinafinal.madinaeshop.adapter.PopularAdapter
import com.madinafinal.madinaeshop.databinding.FragmentHomeBinding
import kotlin.collections.ArrayList
import android.view.View as View1


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
    ): View1 {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container,false)

        binding.ViewallmenuButton.setOnClickListener {
            val bottomSheetDialog= MenuBottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager,"test")
        }

        return binding.root


    }

    override fun onViewCreated(view: View1, savedInstanceState: Bundle?) {
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


        val foodName= listOf("জয়তুন ফল","সরিষা মধু","খলিসা ফুলের মধু","সুস্বাধু ড্রাই ফুড")
        val price= listOf("৳ ২০০","৳ ৫৯৯","৳ ৮০০","৳ ৫৫০")
        val imagePopular= listOf(R.drawable.jaytun_banner2,
            R.drawable.sorisamodhu_banner,
            R.drawable.khalisa_banner,
            R.drawable.dryfood_banner)

        val adapter = PopularAdapter(foodName,price,imagePopular,requireContext())
        binding.PopularRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.PopularRecyclerView.adapter = adapter


    }
}
