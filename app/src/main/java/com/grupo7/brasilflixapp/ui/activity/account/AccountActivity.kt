package com.grupo7.brasilflixapp.ui.activity.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.ActivityAccountBinding
import com.grupo7.brasilflixapp.databinding.ActivitySearchBinding

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding?.backSearch?.setOnClickListener {
            this?.onBackPressed()
        }
    }
}