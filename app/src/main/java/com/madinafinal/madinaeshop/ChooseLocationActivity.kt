package com.madinafinal.madinaeshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.madinafinal.madinaeshop.databinding.ActivityChooseLocationBinding

class ChooseLocationActivity : AppCompatActivity() {
    private val binding: ActivityChooseLocationBinding by lazy {
        ActivityChooseLocationBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val LocatinoList = arrayOf("খুলনা","ঢাকা","বরিশাল","চট্টগ্রাম","ময়মনসিংহ","রংপুর","সিলেট","রাজশাহী")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,LocatinoList)
        val autoCompleteTextView = binding.ListOfLocation
        autoCompleteTextView.setAdapter(adapter)

    }
}