package com.grupo7.brasilflixapp.ui.fragments.favorites.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo7.brasilflixapp.database.favorites.database.FavoritesDatabase
import com.grupo7.brasilflixapp.databinding.FragmentFavoritesBinding
import com.grupo7.brasilflixapp.database.favorites.model.Favorites
import com.grupo7.brasilflixapp.database.popular.database.PopularDatabase
import com.grupo7.brasilflixapp.database.popular.model.Popular
import com.grupo7.brasilflixapp.ui.fragments.favorites.adapter.FavoritesAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FavoritesFragment : Fragment() {

    private var binding: FragmentFavoritesBinding? = null
    private var favoriteslist: List<Popular>? = null

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


        GlobalScope.launch {
            context?.let { contextNonNull ->
                favoriteslist = PopularDatabase.getDatabase(
                    contextNonNull
                ).popularDao().getAllPopular()
            }
        }

        val favoritesAdapter = favoriteslist?.let { FavoritesAdapter(it) }

        binding?.let {
            with(it) {
                favoritesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                favoritesRecyclerView.adapter = favoritesAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }



}