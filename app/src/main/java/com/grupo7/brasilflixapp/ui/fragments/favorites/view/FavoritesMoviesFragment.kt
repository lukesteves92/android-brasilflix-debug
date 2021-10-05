package com.grupo7.brasilflixapp.ui.fragments.favorites.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentFavoritesMoviesBinding
import com.grupo7.brasilflixapp.databinding.FragmentInitialBinding
import com.grupo7.brasilflixapp.ui.fragments.favorites.adapter.FavoritesAdapter
import com.grupo7.brasilflixapp.ui.fragments.favorites.viewmodel.FavoritesViewModel


class FavoritesMoviesFragment : Fragment() {

    private var binding: FragmentFavoritesMoviesBinding? = null
    private lateinit var viewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(it)[FavoritesViewModel::class.java]

            viewModel.getFavoritesMovieFromDb()

            setupObservablesMovies()


        }

    }

    private fun setupObservablesMovies() {
        viewModel.onSuccessFavoritesMoviesFromDb.observe(viewLifecycleOwner, {
            it?.let {
                if (it.isEmpty()) {
                    binding?.apply {
                        favoritesRecyclerViewMovies.isVisible = false
                        birdMovies.isVisible = true
                    }
                } else {
                    val favoritesAdapter = FavoritesAdapter(it) {
                        viewModel.removeFavoritesMovieDb(it)
                        viewModel.getFavoritesMovieFromDb()
                    }
                    binding?.favoritesRecyclerViewMovies?.apply {
                        layoutManager =
                            GridLayoutManager(context, 2)
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