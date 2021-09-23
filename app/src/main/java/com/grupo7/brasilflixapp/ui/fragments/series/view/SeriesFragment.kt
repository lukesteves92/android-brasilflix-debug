package com.grupo7.brasilflixapp.ui.fragments.series.view

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
import com.grupo7.brasilflixapp.ui.fragments.series.adapter.seriesAdapter
import com.grupo7.brasilflixapp.databinding.FragmentSeriesBinding
import com.grupo7.brasilflixapp.model.series.Series
import com.grupo7.brasilflixapp.ui.fragments.home.adapter.filmsAdapter
import com.grupo7.brasilflixapp.ui.fragments.series.viewmodel.SeriesViewModel
import com.grupo7.brasilflixapp.util.constants.Constants


class seriesFragment : Fragment() {

    private var binding: FragmentSeriesBinding? = null
    private lateinit var viewModel: SeriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ------------- Chamando ViewModel -------------//

        activity?.let {
            viewModel = ViewModelProvider(it)[SeriesViewModel::class.java]

            viewModel.command = MutableLiveData()

            setupObservablesSeries()
            setupRecyclerViewSeries()

        }




    }

//    <------------------------------------------------------ Setup Page 2 - Series originals -------------------------------------->

    private val seriesAdapter: seriesAdapter by lazy {
        seriesAdapter { series ->
            val bundle = Bundle()
            bundle.putInt(Constants.Home.KEY_BUNDLE_SERIE_ID, series.id ?: -1)
            findNavController().navigate(
                R.id.action_seriesFragment_to_detailFragment,
                bundle
            )
        }
    }

    private fun setupObservablesSeries() {
        viewModel.seriesPagedList?.observe(viewLifecycleOwner, {
            seriesAdapter.submitList(it)
        })

    }

    private fun setupRecyclerViewSeries() {
        binding?.seriesRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = seriesAdapter
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