package com.grupo7.brasilflixapp.ui.activity.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.ActivityAccountBinding
import com.grupo7.brasilflixapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding?.backSearch?.setOnClickListener {
            this?.onBackPressed()
        }
    }
}