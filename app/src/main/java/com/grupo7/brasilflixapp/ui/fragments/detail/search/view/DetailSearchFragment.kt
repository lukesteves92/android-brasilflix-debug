package com.grupo7.brasilflixapp.ui.fragments.detail.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentDetailBinding
import com.grupo7.brasilflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesSeries
import com.grupo7.brasilflixapp.databinding.FragmentDetailSearchBinding
import com.grupo7.brasilflixapp.ui.fragments.detail.main.adapter.DetailReviewSearchAdapter
import com.grupo7.brasilflixapp.ui.fragments.detail.main.viewmodel.DetailSearchViewModel
import com.grupo7.brasilflixapp.util.constants.Constants.Home.KEY_BUNDLE_MOVIE_ID
import com.grupo7.brasilflixapp.util.constants.Constants.Home.KEY_BUNDLE_SERIE_ID
import com.grupo7.brasilflixapp.util.constants.Constants.Series.KET_BUNDLE_SERIES


class DetailSearchFragment(
) : Fragment() {
    private var binding: FragmentDetailSearchBinding? = null
    private lateinit var detailsearchViewModel: DetailSearchViewModel
    private val movieId: Int by lazy {
        arguments?.getInt(KEY_BUNDLE_MOVIE_ID) ?: -1
    }

    private val serieId: Int by lazy {
        arguments?.getInt(KEY_BUNDLE_SERIE_ID) ?: -1
    }

    private val serieFragment: Int by lazy {
        arguments?.getInt(KET_BUNDLE_SERIES) ?: -1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            detailsearchViewModel = ViewModelProvider(it)[DetailSearchViewModel::class.java]

            detailsearchViewModel.command = MutableLiveData()

            detailsearchViewModel.getReviewsMoviesSearch(movieId)

            detailsearchViewModel.getMovieByIdSearch(movieId)

            detailsearchViewModel.getSerieByIdFromDbSearch(serieId)

            setupReviewsMovies()

            setupDetailMovie()

            setupDetailSerie()

        }

        binding?.ivMenu?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding?.ivHeart?.setOnClickListener {


            if (serieFragment == 50) {
                detailsearchViewModel.onSuccessSerieDbByIdFromDb.observe(viewLifecycleOwner, {

                    val id = it.id
                    val poster = it.poster_path
                    val title = it.original_name
                    val favorite = FavoritesSeries(id, poster, title)
                    detailsearchViewModel.saveFavoritesSeriesDbSearch(favorite)

                    Snackbar.make(
                        this.requireView(),
                        getString(R.string.favoriteadded),
                        Snackbar.LENGTH_SHORT
                    ).show()
                })

            } else {
                detailsearchViewModel.onSuccessMovieById.observe(viewLifecycleOwner, {


                    val id = it.id
                    val poster = it.poster_path
                    val title = it.title
                    val favorite = Favorites(id, poster, title)
                    detailsearchViewModel.saveFavoritesDbSearch(favorite)


                    Snackbar.make(
                        this.requireView(),
                        getString(R.string.favoriteadded),
                        Snackbar.LENGTH_SHORT
                    ).show()
                })


            }
        }
    }

    private fun setupDetailMovie() {

        detailsearchViewModel.onSuccessMovieById.observe(viewLifecycleOwner, {
            it?.let { movie ->
                binding?.let { bindingNonNull ->
                    with(bindingNonNull) {
                        activity?.let { activityNonNull ->
                            Glide.with(activityNonNull)
                                .load(movie.backdrop_path)
                                .placeholder(R.drawable.brflixlogo)
                                .into(imageCardDetail)
                        }
                        tvTitle.text = movie.title
                        tvTextSummary.text = movie.overview
                        dateCardDetail.text = ("Data de lançamento:  ${movie.release_date}")
                    }
                }
            }
        })


    }

    private fun setupDetailSerie() {

        detailsearchViewModel.onSuccessSerieDbByIdFromDb.observe(viewLifecycleOwner, {
            it?.let { serie ->
                binding?.let { bindingNonNull ->
                    with(bindingNonNull) {
                        activity?.let { activityNonNull ->
                            Glide.with(activityNonNull)
                                .load(serie.poster_path)
                                .placeholder(R.drawable.brflixlogo)
                                .into(imageCardDetail)
                        }
                        tvTitle.text = serie.original_name
                        tvTextSummary.text = serie.overview
                        dateCardDetail.text = ("Data de lançamento:  ${serie.first_air_date}")
                    }
                }
            }
        })


    }

    private fun setupReviewsMovies() {
        detailsearchViewModel.onSuccessReviewsMovies.observe(viewLifecycleOwner, {
            it?.let {
                val ReviewsAdapter = DetailReviewSearchAdapter(it)
                binding?.let {
                    with(it) {
                        reviewsRecyclerView.layoutManager = LinearLayoutManager(context)
                        reviewsRecyclerView.adapter = ReviewsAdapter
                        reviewsRecyclerView.adapter?.stateRestorationPolicy = RecyclerView
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