package com.grupo7.brasilflixapp.ui.fragments.initial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grupo7.brasilflixapp.ui.fragments.initial.view.initialVPAdapter
import com.grupo7.brasilflixapp.databinding.FragmentInitialBinding
import com.grupo7.brasilflixapp.ui.fragments.login.LoginFragment
import com.grupo7.brasilflixapp.ui.fragments.register.RegisterFragment


class initialFragment : Fragment() {

    private var binding: FragmentInitialBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInitialBinding.inflate(inflater, container, false)
        return binding?.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentsList = listOf(LoginFragment(), RegisterFragment())
        val fragmentsTitleList = listOf("Login", "Cadastro")

        activity?.let {
            val viewPagerAdapter = initialVPAdapter(
                fragmentManager = childFragmentManager,
                fragmentsList = fragmentsList,
                fragmentsTitleList = fragmentsTitleList
            )
            binding?.let { bindingNonNull ->
                with(bindingNonNull) {
                    vpPages.adapter = viewPagerAdapter
                    tabLayout.setupWithViewPager(vpPages)

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}