package com.example.gameofthroneshouses.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.gameofthroneshouses.R
import com.example.gameofthroneshouses.databinding.ActivityDetailsBinding
import com.example.gameofthroneshouses.utils.HOUSE_NAME
import com.example.gameofthroneshouses.utils.log

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)
        val name = intent.getStringExtra(HOUSE_NAME)
        log { "current item $name" }
    }
}