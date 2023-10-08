package com.madinafinal.madinaeshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madinafinal.madinaeshop.Freagment.HomeFragment
import com.madinafinal.madinaeshop.databinding.ActivityPayOutBinding

class PayOutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPayOutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPayOutBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.payoutBackBtn.setOnClickListener {
            finish()
        }

        binding.GoConrat.setOnClickListener {
            val BottomSheetDaylog = CongratBottomSheetFragment()
            BottomSheetDaylog.show(supportFragmentManager,"test")
        }
    }
}