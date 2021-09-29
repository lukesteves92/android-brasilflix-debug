package com.grupo7.brasilflixapp.ui.fragments.preferences

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grupo7.brasilflixapp.databinding.FragmentPreferencesBinding
import com.grupo7.brasilflixapp.ui.activity.home.HomeActivity


class PreferencesFragment : Fragment() {

    private var binding: FragmentPreferencesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPreferencesBinding.inflate(inflater,container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.tvPreferencesNext?.setOnClickListener {
            startActivity(Intent(activity, HomeActivity::class.java))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}