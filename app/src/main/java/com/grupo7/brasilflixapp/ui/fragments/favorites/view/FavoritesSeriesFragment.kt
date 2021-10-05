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
import com.grupo7.brasilflixapp.databinding.FragmentFavoritesSeriesBinding
import com.grupo7.brasilflixapp.databinding.FragmentInitialBinding
import com.grupo7.brasilflixapp.ui.fragments.favorites.adapter.FavoritesSeriesAdapter
import com.grupo7.brasilflixapp.ui.fragments.favorites.viewmodel.FavoritesViewModel


class FavoritesSeriesFragment : Fragment() {

    private var binding: FragmentFavoritesSeriesBinding? = null
    private lateinit var viewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesSeriesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(it)[FavoritesViewModel::class.java]

            viewModel.getFavoritesSeriesFromDb()

            setupObservablesSeries()

        }
    }

    private fun setupObservablesSeries() {
        viewModel.onSuccessFavoritesSeriesFromDb.observe(viewLifecycleOwner, {
            it?.let {
                if (it.isNullOrEmpty()) {
                    binding?.favoritesRecyclerViewSeries?.isVisible = false
                    binding?.birdSeries?.isVisible = true
                } else {

                    val favoritesAdapter = FavoritesSeriesAdapter(it) {
                        viewModel.removeFavoritesSeriesDb(it)
                        viewModel.getFavoritesSeriesFromDb()
                    }
                    binding?.favoritesRecyclerViewSeries?.apply {
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