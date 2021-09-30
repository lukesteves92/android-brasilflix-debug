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
import com.grupo7.brasilflixapp.ui.fragments.favorites.viewmodel.FavoritesViewModel


class FavoritesFragment : Fragment() {

    private var binding: FragmentFavoritesBinding? = null
    private lateinit var viewModel: FavoritesViewModel

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


        activity?.let {
            viewModel = ViewModelProvider(it)[FavoritesViewModel::class.java]

            viewModel.getFavoritesMovieFromDb()
            viewModel.getFavoritesSeriesFromDb()

            setupObservablesMovies()

            setupObservablesSeries()

        }

    }

    private fun setupObservablesMovies() {
        viewModel.onSuccessFavoritesMoviesFromDb.observe(viewLifecycleOwner, {
            it?.let {
                if (it.isEmpty()) {
                    binding?.apply {
                        favoritesRecyclerView.isVisible = false
                        birdMovies.isVisible = true
                        textSeries.isVisible = true
                        imageSeries.isVisible = false
                    }
                } else {
                    val favoritesAdapter = FavoritesAdapter(it) {
                        viewModel.removeFavoritesMovieDb(it)
                        viewModel.getFavoritesMovieFromDb()
                    }
                    binding?.favoritesRecyclerView?.apply {
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = favoritesAdapter
                        adapter?.stateRestorationPolicy = RecyclerView
                            .Adapter.StateRestorationPolicy
                            .PREVENT_WHEN_EMPTY
                    }

                }
            }

        })

    }

    private fun setupObservablesSeries() {
        viewModel.onSuccessFavoritesSeriesFromDb.observe(viewLifecycleOwner, {
            it?.let {
                if (it.isEmpty()) {
                    binding?.favoritesRecyclerViewSeries?.isVisible = false
                    binding?.birdSeries?.isVisible = true
                } else {

                    val favoritesAdapter = FavoritesSeriesAdapter(it) {
                        viewModel.removeFavoritesSeriesDb(it)
                        viewModel.getFavoritesSeriesFromDb()
                    }
                    binding?.favoritesRecyclerViewSeries?.apply {
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = favoritesAdapter
                        adapter?.stateRestorationPolicy = RecyclerView
                            .Adapter.StateRestorationPolicy
                            .PREVENT_WHEN_EMPTY
                    }
                }
            }

        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}