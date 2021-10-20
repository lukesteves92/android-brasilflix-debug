package com.grupo7.brasilflixapp.ui.fragments.home.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.base.BaseFragment
import com.grupo7.brasilflixapp.data.api.util.Command
import com.grupo7.brasilflixapp.ui.activity.search.SearchActivity
import com.grupo7.brasilflixapp.ui.fragments.home.adapter.filmsAdapter
import com.grupo7.brasilflixapp.databinding.FragmentHomeBinding
import com.grupo7.brasilflixapp.ui.activity.profile.ProfileActivity
import com.grupo7.brasilflixapp.ui.fragments.home.adapter.upcomingAdapter
import com.grupo7.brasilflixapp.ui.fragments.home.viewmodel.HomeViewModel
import com.grupo7.brasilflixapp.ui.fragments.favorites.adapter.popularAdapter
import com.grupo7.brasilflixapp.util.constants.Constants
import com.grupo7.brasilflixapp.util.constants.Constants.Home.KEY_BUNDLE_MOVIE_ID
import com.grupo7.brasilflixapp.util.constants.Constants.Login.UserName
import com.squareup.picasso.Picasso


class HomeFragment : BaseFragment() {
    private var binding: FragmentHomeBinding? = null
    var fragments: List<Fragment>? = null
    private lateinit var viewModel: HomeViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }
    // ------------- Configuração Top Bar -------------//

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ------------- Chamando ViewModel -------------//

        activity?.let {
            viewModel = ViewModelProvider(it)[HomeViewModel::class.java]

            viewModel.command = command

            Handler(Looper.getMainLooper()).postDelayed({
                view.post {
                    binding?.layoutRecycleMain?.isVisible = true
                    binding?.loadingLottieHome?.isVisible = false
                    setupObservablesToprated()
                    setupRecyclerViewToprated()
                    setupObservablesUpComing()
                    setupRecyclerViewUpComing()
                    setupObservablesPopular()
                    setupRecyclerViewPopular()
                }
            }, 1000L)
        }

        loadProfileImageFromStorageAndUserName()

        binding?.userHomeText?.text = "Olá, $UserName"

        binding?.searchHome?.setOnClickListener{
            startActivity(Intent(activity, SearchActivity::class.java))
        }

        binding?.pictureProfileCard?.setOnClickListener{
            startActivity(Intent(activity, ProfileActivity::class.java))
        }


    }

//    <------------------------------------------------------ Setup Page 2 - TopRated -------------------------------------->

    private val filmsAdapter: filmsAdapter by lazy {
        filmsAdapter { topRated ->
            val bundle = Bundle()
            bundle.putInt(KEY_BUNDLE_MOVIE_ID, topRated.id ?: -1)
            findNavController().navigate(
                R.id.action_HomeFragment_to_detailFragment,
                bundle
            )
        }
    }

    private fun setupObservablesToprated() {
        viewModel.topRatedPagedList?.observe(viewLifecycleOwner, {
            filmsAdapter.submitList(it)
        })

        viewModel.command.observe(viewLifecycleOwner, {
            when (it) {
                is Command.Loading -> {

                }
                is Command.Error -> {

                }
            }
        })

    }

    private fun setupRecyclerViewToprated() {
        binding?.filmesRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = filmsAdapter
            adapter?.stateRestorationPolicy = RecyclerView
                .Adapter.StateRestorationPolicy
                .PREVENT_WHEN_EMPTY
        }
    }

    //    <------------------------------------------------------ Setup Page 2 - UpComing -------------------------------------->

    private val upcomingAdapter: upcomingAdapter by lazy {
        upcomingAdapter { upcoming ->
            val bundle = Bundle()
            bundle.putInt(KEY_BUNDLE_MOVIE_ID, upcoming.id ?: -1)
            findNavController().navigate(
                R.id.action_HomeFragment_to_detailFragment,
                bundle
            )
        }
    }

    private fun setupObservablesUpComing() {
        viewModel.upComingPagedList?.observe(viewLifecycleOwner, {
            upcomingAdapter.submitList(it)
        })

        viewModel.command.observe(viewLifecycleOwner, {
            when (it) {
                is Command.Loading -> {

                }
                is Command.Error -> {

                }
            }
        })

    }
    private fun setupRecyclerViewUpComing() {
        binding?.upcomingRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = upcomingAdapter
            adapter?.stateRestorationPolicy = RecyclerView
                .Adapter.StateRestorationPolicy
                .PREVENT_WHEN_EMPTY
        }
    }

    //    <------------------------------------------------------ Setup Page 2 - Popular -------------------------------------->

    private val popularAdapter: popularAdapter by lazy {
        popularAdapter { popular ->
            val bundle = Bundle()
            bundle.putInt(KEY_BUNDLE_MOVIE_ID, popular.id ?: -1)
            findNavController().navigate(
                R.id.action_HomeFragment_to_detailFragment,
                bundle
            )
        }
    }

    private fun setupObservablesPopular() {
        viewModel.popularPagedList?.observe(viewLifecycleOwner, {
            popularAdapter.submitList(it)
        })

        viewModel.command.observe(viewLifecycleOwner, {
            when (it) {
                is Command.Loading -> {

                }
                is Command.Error -> {

                }
            }
        })

    }
    private fun setupRecyclerViewPopular() {
        binding?.popularRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
            adapter?.stateRestorationPolicy = RecyclerView
                .Adapter.StateRestorationPolicy
                .PREVENT_WHEN_EMPTY
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun loadProfileImageFromStorageAndUserName() {
        val firebase = FirebaseStorage.getInstance()
        val storage = firebase.getReference("UserProfileImages")
        
        try {
            storage.child("${Constants.Login.UserID}.jpeg").downloadUrl.addOnSuccessListener {
                Picasso.get()
                    .load(it.toString())
                    .placeholder(R.drawable.nophoto)
                    .into(binding?.photoProfile)

            }
        } catch(e: Exception){
            Picasso.get()
                .load(R.drawable.nophoto)
                .into(binding?.photoProfile)
        }



    }

    override var command: MutableLiveData<Command> = MutableLiveData()

}