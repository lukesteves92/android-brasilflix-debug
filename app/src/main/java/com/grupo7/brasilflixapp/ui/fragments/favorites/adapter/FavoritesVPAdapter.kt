package com.grupo7.brasilflixapp.ui.fragments.favorites.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FavoritesVPAdapter (
    private val fragmentsList: List<Fragment>,
    private val fragmentsTitleList: List<String>,
    val fragmentManager: FragmentManager
): FragmentPagerAdapter(fragmentManager) {

    override fun getCount() = fragmentsList.size

    override fun getItem(position: Int) = fragmentsList[position]

    override fun getPageTitle(position: Int) = fragmentsTitleList[position]
}
