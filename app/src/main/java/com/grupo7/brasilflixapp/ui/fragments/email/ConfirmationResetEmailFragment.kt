package com.grupo7.brasilflixapp.ui.fragments.email

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentChangeEmailBinding
import com.grupo7.brasilflixapp.databinding.FragmentConfirmationResetEmailBinding
import com.grupo7.brasilflixapp.ui.activity.home.HomeActivity


class ConfirmationResetEmailFragment : Fragment() {

    private var binding: FragmentConfirmationResetEmailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmationResetEmailBinding.inflate(inflater,container,false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btGoToHome?.setOnClickListener {
            startActivity(Intent(this.requireActivity(), HomeActivity::class.java))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}