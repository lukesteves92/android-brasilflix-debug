package com.grupo7.brasilflixapp.ui.activity.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.ui.activity.home.HomeActivity
import com.grupo7.brasilflixapp.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding?.topAppBar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.backbutton -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                else -> false
            }
        }

    }
}