package com.grupo7.brasilflixapp.ui.activity.changeEmail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupo7.brasilflixapp.databinding.ActivityChangeEmailBinding

class ChangeEmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangeEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}