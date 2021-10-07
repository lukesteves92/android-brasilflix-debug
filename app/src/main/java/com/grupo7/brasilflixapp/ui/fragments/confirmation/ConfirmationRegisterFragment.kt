package com.grupo7.brasilflixapp.ui.fragments.confirmation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentConfirmationRegisterBinding
import com.grupo7.brasilflixapp.databinding.FragmentLoginBinding


class ConfirmationRegisterFragment : Fragment() {

    private var binding: FragmentConfirmationRegisterBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmationRegisterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btGoToLogin?.setOnClickListener{
            findNavController().navigate(R.id.action_confirmationRegisterFragment_to_initialFragment)
        }
    }


}