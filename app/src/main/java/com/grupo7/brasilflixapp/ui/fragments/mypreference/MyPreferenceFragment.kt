package com.grupo7.brasilflixapp.ui.fragments.mypreference

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.grupo7.brasilflixapp.databinding.FragmentMyPreferenceBinding

class MyPreferenceFragment : Fragment() {

    private var binding: FragmentMyPreferenceBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyPreferenceBinding.inflate(inflater,container,false)
        return binding?.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}