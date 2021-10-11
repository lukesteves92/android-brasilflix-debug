package com.grupo7.brasilflixapp.ui.fragments.videos.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentVideosBinding
import com.grupo7.brasilflixapp.ui.activity.main.MainActivity
import com.grupo7.brasilflixapp.ui.fragments.videos.adapter.VideosAdapter
import com.grupo7.brasilflixapp.ui.fragments.videos.viewmodel.VideosViewModel
import com.grupo7.brasilflixapp.util.constants.Constants.Detail.KEY_BUNDLE_VIDEO_ID_MOVIE
import com.grupo7.brasilflixapp.util.constants.Constants.Detail.KEY_BUNDLE_VIDEO_ID_SERIE


class VideosFragment : Fragment() {

    private var binding: FragmentVideosBinding? = null
    private lateinit var viewModel: VideosViewModel
    private val serieId: Int by lazy {
        arguments?.getInt(KEY_BUNDLE_VIDEO_ID_SERIE) ?: -1
    }

    private val movieId: Int by lazy {
        arguments?.getInt(KEY_BUNDLE_VIDEO_ID_MOVIE) ?: -1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(it)[VideosViewModel::class.java]

            viewModel.command = MutableLiveData()

            viewModel.getMoviesVideos(movieId)

            viewModel.getSeriesVideos(serieId)

            setupObservablesMovies()

            setupObservablesSeries()
        }

        binding?.ivMenu?.setOnClickListener {
            activity?.onBackPressed()
        }

    }

    private fun setupObservablesMovies() {
        viewModel.onSuccessMoviesVideos?.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) {
                binding?.notrailerCard?.isVisible = true
                binding?.videosRecyclerView?.isVisible = false
            } else {
                it.let {
                    val VideosAdapter = VideosAdapter(it) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.key)))
                    }
                    binding?.let {
                        with(it) {
                            videosRecyclerView.layoutManager = LinearLayoutManager(context)
                            videosRecyclerView.adapter = VideosAdapter
                            videosRecyclerView.adapter?.stateRestorationPolicy = RecyclerView
                                .Adapter.StateRestorationPolicy
                                .PREVENT_WHEN_EMPTY
                        }
                    }
                }
            }
        })

    }

    private fun setupObservablesSeries() {
        viewModel.onSuccessSeriesVideos?.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                binding?.notrailerCard?.isVisible = true
                binding?.videosRecyclerView?.isVisible = false
            } else {
                it?.let {
                    val VideosAdapter = VideosAdapter(it) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.key)))
                    }
                    binding?.let {
                        with(it) {
                            videosRecyclerView.layoutManager = LinearLayoutManager(context)
                            videosRecyclerView.adapter = VideosAdapter
                            videosRecyclerView.adapter?.stateRestorationPolicy = RecyclerView
                                .Adapter.StateRestorationPolicy
                                .PREVENT_WHEN_EMPTY
                        }
                    }
                }
            }
        })

    }


}