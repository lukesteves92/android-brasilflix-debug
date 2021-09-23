package com.grupo7.brasilflixapp.ui.activity.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.navigation.Navigation
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSmoothBottomMenu()
    }


    private fun setupSmoothBottomMenu() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.bottom_nav_home)
        val menu = popupMenu.menu
        binding.bottomBar.setupWithNavController(menu, navController)
    }




}