package com.grupo7.brasilflixapp.ui.activity.myPreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupo7.brasilflixapp.databinding.ActivityMyPreferenceBinding

class MyPreferenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyPreferenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
