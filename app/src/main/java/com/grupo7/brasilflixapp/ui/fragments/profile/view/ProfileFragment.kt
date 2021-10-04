package com.grupo7.brasilflixapp.ui.fragments.profile.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo7.brasilflixapp.databinding.FragmentProfileBinding
import com.grupo7.brasilflixapp.ui.model.profile.ItemProfile
import com.grupo7.brasilflixapp.util.enumarators.ProfileItemActionEnum
import com.grupo7.brasilflixapp.util.interfaces.IProfileItemClick


class profileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }




}