package com.grupo7.brasilflixapp.ui.fragments.search.view


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.ui.fragments.search.adapter.searchAdapter
import com.grupo7.brasilflixapp.databinding.FragmentSearchBinding
import com.grupo7.brasilflixapp.extensions.hideKeyboard
import com.grupo7.brasilflixapp.ui.fragments.search.adapter.SearchAdapterSeries
import com.grupo7.brasilflixapp.ui.fragments.search.viewmodel.SearchViewModel
import com.grupo7.brasilflixapp.ui.model.films.films
import com.grupo7.brasilflixapp.ui.model.series.Series
import com.grupo7.brasilflixapp.util.constants.Constants


class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // ------------- Chamando ViewModel -------------//

        activity?.let {
            viewModel = ViewModelProvider(it)[SearchViewModel::class.java]

            viewModel.command = MutableLiveData()

            binding?.boxMovie?.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked) {
                    binding?.boxSeries?.isVisible = false
                    binding?.searchField?.setOnQueryTextListener(object :
                        androidx.appcompat.widget.SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            query?.let {
                                viewModel.searchMovies(it)
                            }
                            return true
                        }

                        override fun onQueryTextChange(query: String?): Boolean {
                            query?.let {
                                viewModel.searchMovies(it)
                            }
                            return true
                        }
                    })
                    this.view?.hideKeyboard()
                } else{
                    binding?.boxSeries?.isVisible = true
                }
            }


            binding?.boxSeries?.setOnCheckedChangeListener{ buttonView, isChecked ->
                if(isChecked) {
                    binding?.boxMovie?.isVisible = false
                    binding?.searchField?.setOnQueryTextListener(object :
                        androidx.appcompat.widget.SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            query?.let {
                                viewModel.searchSeries(it)
                            }
                            return true
                        }

                        override fun onQueryTextChange(query: String?): Boolean {
                            query?.let {
                                viewModel.searchSeries(it)
                            }
                            return true
                        }
                    })
                    this.view?.hideKeyboard()
                } else{
                    binding?.boxMovie?.isVisible = true
                }
            }


        }
        // ------------- Setar dados ViewModel no RecycleView -------------//

        viewModel.onSuccessSearch.observe(viewLifecycleOwner, {
            it?.let {
                showSearch(it)
            }
        })

        viewModel.onSuccessSearchSeries.observe(viewLifecycleOwner, {
            it?.let {
                showSearchSeries(it)
            }
        })


    }

    private fun showSearch(filmsList: List<films>) {
        filmsList.forEach {
            val searchAdapter = searchAdapter(filmsList){
                val bundle = Bundle()
                bundle.putInt(Constants.Home.KEY_BUNDLE_MOVIE_ID, it.id ?: -1)
                findNavController().navigate(
                    R.id.action_searchFragment_to_detailSearchFragment,
                    bundle
                )
            }
            binding?.let {
                with(it) {
                    searchRecyclerView.layoutManager = GridLayoutManager(context, 2)
                    searchRecyclerView.adapter = searchAdapter
                }
            }


        }
    }

    private fun showSearchSeries(seriesList: List<Series>) {
        seriesList.forEach {
            val searchAdapter = SearchAdapterSeries(seriesList){
                val bundle = Bundle()
                bundle.putInt(Constants.Home.KEY_BUNDLE_SERIE_ID, it.id ?: -1)
                bundle.putInt(Constants.Series.KET_BUNDLE_SERIES, 50)
                findNavController().navigate(
                    R.id.action_searchFragment_to_detailSearchFragment,
                    bundle
                )
            }
            binding?.let {
                with(it) {
                    searchRecyclerView.layoutManager = GridLayoutManager(context, 2)
                    searchRecyclerView.adapter = searchAdapter
                }
            }
        }

    }




    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}






