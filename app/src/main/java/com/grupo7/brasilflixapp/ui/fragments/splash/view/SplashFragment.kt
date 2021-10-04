package com.grupo7.brasilflixapp.ui.fragments.splash.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentLoginBinding
import com.grupo7.brasilflixapp.databinding.FragmentSplashBinding
import com.grupo7.brasilflixapp.ui.activity.main.MainActivity
import com.grupo7.brasilflixapp.ui.fragments.splash.viewmodel.SplashViewModel


class SplashFragment : Fragment() {

    private lateinit var _view: View
    private var binding: FragmentSplashBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view

        val viewModel = ViewModelProvider(this,
            SplashViewModel.SplashViewModelFactory(_view.context))[SplashViewModel::class.java]

        viewModel.checarOnline().observe(viewLifecycleOwner, {
            if(it){
                Handler().postDelayed({
                    view?.post {
//                        findNavController().navigate(R.id.action_splashFragment_to_inicial_nav)
                        startActivity(Intent(activity, MainActivity::class.java))
                    }
                    onDestroyView()
                }, 2000L)
            } else{
                context?.let { it1 ->
                    MaterialAlertDialogBuilder(it1)
                        .setTitle(resources.getString(R.string.dialogplashText1))
                        .setMessage(resources.getString(R.string.limitedFunctionsSplash))
                        .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                            startActivity(Intent(activity, MainActivity::class.java))
                            onDestroyView()
                        }
                        .show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}