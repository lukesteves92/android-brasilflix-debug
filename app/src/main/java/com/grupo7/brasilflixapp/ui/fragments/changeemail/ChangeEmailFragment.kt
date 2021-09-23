package com.grupo7.brasilflixapp.ui.fragments.changeemail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grupo7.brasilflixapp.databinding.FragmentChangeEmailBinding


class ChangeEmailFragment : Fragment() {

    private var binding: FragmentChangeEmailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChangeEmailBinding.inflate(inflater,container,false)
        return binding?.root

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btConfirm?.setOnClickListener {
//            findNavController().navigate(R.id.action_changeEmailFragment_to_detailFragment)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}