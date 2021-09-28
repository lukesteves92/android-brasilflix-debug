package com.grupo7.brasilflixapp.ui.fragments.search.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo7.brasilflixapp.ui.fragments.search.adapter.searchAdapter
import com.grupo7.brasilflixapp.databinding.FragmentSearchBinding
import com.grupo7.brasilflixapp.ui.fragments.search.viewmodel.SearchViewModel
import com.grupo7.brasilflixapp.model.films.films


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

                binding?.searchField?.setOnQueryTextListener(object :
                    androidx.appcompat.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let {
                            viewModel.searchMovies(it)
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })


            }
          // ------------- Setar dados ViewModel no RecycleView -------------//

            viewModel.onSuccessSearch.observe(viewLifecycleOwner, {
                it?.let {
                    showTopRated(it)
                }
            })


        }

    private fun showTopRated(filmsList: List<films>) {
        filmsList.forEach {
            val searchAdapter = searchAdapter(filmsList)
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






