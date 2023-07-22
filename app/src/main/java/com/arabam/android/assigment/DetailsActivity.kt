package com.arabam.android.assigment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arabam.android.assigment.databinding.DetailedMainBinding

private lateinit var binding: DetailedMainBinding
class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailedMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val advertID= intent.getIntExtra("Advert",0)
        if(advertID==0){
            println("Advert couldn't found")
        }else{

        }
    }
}


