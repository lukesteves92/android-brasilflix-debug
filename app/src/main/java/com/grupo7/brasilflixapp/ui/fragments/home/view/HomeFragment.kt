package com.grupo7.brasilflixapp.ui.fragments.home.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.database.popular.model.Popular
import com.grupo7.brasilflixapp.ui.activity.account.AccountActivity
import com.grupo7.brasilflixapp.ui.activity.profile.ProfileActivity
import com.grupo7.brasilflixapp.ui.activity.search.SearchActivity
import com.grupo7.brasilflixapp.ui.fragments.home.adapter.filmsAdapter
import com.grupo7.brasilflixapp.databinding.FragmentHomeBinding
import com.grupo7.brasilflixapp.ui.fragments.home.adapter.upcomingAdapter
import com.grupo7.brasilflixapp.ui.fragments.home.viewmodel.HomeViewModel
import com.grupo7.brasilflixapp.ui.fragments.favorites.adapter.popularAdapter
import com.grupo7.brasilflixapp.util.constants.Constants.Home.KEY_BUNDLE_MOVIE_ID
import com.grupo7.brasilflixapp.util.constants.Constants.Home.KEY_BUNDLE_MOVIE_POSTER
import com.grupo7.brasilflixapp.util.constants.Constants.Home.KEY_BUNDLE_MOVIE_TITLE


class HomeFragment : Fragment() {
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

        binding?.topAppBar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.SearchFragment -> {
                    startActivity(Intent(activity, SearchActivity::class.java))
                    true
                }
                R.id.profileFragment -> {
                    startActivity(Intent(activity, ProfileActivity::class.java))
                    true
                }
                R.id.accountFragment -> {
                    startActivity(Intent(activity, AccountActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // ------------- Chamando ViewModel -------------//

        activity?.let {
            viewModel = ViewModelProvider(it)[HomeViewModel::class.java]

            viewModel.command = MutableLiveData()

            setupObservablesToprated()
            setupRecyclerViewToprated()
            setupObservablesUpComing()
            setupRecyclerViewUpComing()
            setupObservablesPopular()
            setupRecyclerViewPopular()

        }

        // ------------- Mostrar ViewPager Tela Home -------------//

//        showViewPagerHome()
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

}