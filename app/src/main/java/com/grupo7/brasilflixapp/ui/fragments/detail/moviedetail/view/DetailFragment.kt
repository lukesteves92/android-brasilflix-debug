package com.grupo7.brasilflixapp.ui.fragments.detail.moviedetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.grupo7.brasilflixapp.R

import com.grupo7.brasilflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brasilflixapp.databinding.FragmentDetailBinding
import com.grupo7.brasilflixapp.ui.fragments.detail.moviedetail.adapter.DetailReviewAdapter
import com.grupo7.brasilflixapp.ui.fragments.detail.moviedetail.viewmodel.DetailViewModel
import com.grupo7.brasilflixapp.util.constants.Constants.Detail.KEY_BUNDLE_VIDEO_ID_MOVIE
import com.grupo7.brasilflixapp.util.constants.Constants.Home.KEY_BUNDLE_MOVIE_ID
import com.grupo7.brasilflixapp.util.share.ShareImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener


class DetailFragment(
) : Fragment() {
    private var binding: FragmentDetailBinding? = null
    private lateinit var detailViewModel: DetailViewModel
    private val movieId: Int by lazy {
        arguments?.getInt(KEY_BUNDLE_MOVIE_ID) ?: -1
    }
    private var imageMovie: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            detailViewModel = ViewModelProvider(it)[DetailViewModel::class.java]

            detailViewModel.command = MutableLiveData()

            detailViewModel.getReviewsMovies(movieId)

            detailViewModel.getMovieById(movieId)

            detailViewModel.getMovieByIdFromDb(movieId)

            detailViewModel.getMoviesVideos(movieId)

            setupReviewsMovies()
            setupDetailMovie()

        }

        binding?.ivMenu?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding?.ivShare?.setOnClickListener {

            binding?.mainViewDetail?.let { it1 ->
                ShareImage.share(
                    this.requireActivity(),
                    it1, "Compartilhando Filmes/S??ries"
                )
            }
        }


        binding?.ivMovie?.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(KEY_BUNDLE_VIDEO_ID_MOVIE, movieId)

            findNavController().navigate(
                R.id.action_detailFragment_to_VideosFragment,
                bundle
            )

        }

        binding?.ivHeart?.setOnClickListener {

            detailViewModel.onSuccessMovieById.observe(viewLifecycleOwner, {

                val id = it.id
                val poster = it.poster_path
                val title = it.title
                val favorite = Favorites(id, poster, title)
                detailViewModel.saveFavoritesDb(favorite)

                Snackbar.make(
                    this.requireView(),
                    getString(R.string.favoriteadded),
                    Snackbar.LENGTH_SHORT
                ).show()
            })


        }
    }

    private fun setupImageOrVideo(imageMovie: String?) {
        detailViewModel.onSuccessMoviesVideos.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                val youtube = it.last()
                binding?.apply {
                    youtubePlayerDetail.addYouTubePlayerListener(object :
                        AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youtube.key?.let { it1 -> youTubePlayer.loadVideo(it1, 0f) }
                        }
                        })
                    youtubePlayerDetail.isFullScreen()
                }
            } else {
                binding?.apply {
                    youtubePlayerDetail.isVisible = false
                    imageCardDetail.isVisible = true
                    activity?.let { activityNonNull ->
                        Glide.with(activityNonNull)
                            .load(imageMovie)
                            .placeholder(R.drawable.brflixlogo)
                            .override(900, 500)
                            .into(imageCardDetail)
                    }
                }

            }

        })

    }
    private fun setupDetailMovie() {
        detailViewModel.onSuccessMovieDbByIdFromDb.observe(viewLifecycleOwner, {
            it?.let { movie ->
                binding?.let { bindingNonNull ->
                    with(bindingNonNull) {
                        movie.backdrop_path?.let { it1 -> setupImageOrVideo(it1) }
                        tvTitle.text = movie.title
                        tvTextSummary.text = movie.overview
                        dateCardDetail.text = ("Data de lan??amento:  ${movie.release_date}")
                    }
                }
            }
        })

    }

    private fun setupReviewsMovies() {
        detailViewModel.onSuccessReviewsMovies.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) {
                binding?.nocomentsCard?.isVisible = true
                binding?.reviewsRecyclerView?.isVisible = false
            } else {
                it?.let {
                    val ReviewsAdapter = DetailReviewAdapter(it)
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
            }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}