package com.grupo7.brasilflixapp.ui.fragments.favorites.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo7.brasilflixapp.databinding.FragmentFavoritesBinding
import com.grupo7.brasilflixapp.ui.fragments.favorites.adapter.FavoritesAdapter
import com.grupo7.brasilflixapp.ui.fragments.favorites.adapter.FavoritesSeriesAdapter
import com.grupo7.brasilflixapp.ui.fragments.favorites.adapter.FavoritesVPAdapter
import com.grupo7.brasilflixapp.ui.fragments.favorites.viewmodel.FavoritesViewModel
import com.grupo7.brasilflixapp.ui.fragments.initial.view.initialVPAdapter
import com.grupo7.brasilflixapp.ui.fragments.login.LoginFragment
import com.grupo7.brasilflixapp.ui.fragments.register.RegisterFragment


class FavoritesFragment : Fragment() {

    private var binding: FragmentFavoritesBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentsList = listOf(FavoritesMoviesFragment(), FavoritesSeriesFragment())
        val fragmentsTitleList = listOf("Filmes", "Séries")

        activity?.let {
            val viewPagerAdapter = FavoritesVPAdapter(
                fragmentManager = childFragmentManager,
                fragmentsList = fragmentsList,
                fragmentsTitleList = fragmentsTitleList
            )
            binding?.let { bindingNonNull ->
                with(bindingNonNull) {
                    vpPages.adapter = viewPagerAdapter
                    tabLayout.setupWithViewPager(vpPages)

                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}