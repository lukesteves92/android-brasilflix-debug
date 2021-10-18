package com.grupo7.brasilflixapp.ui.fragments.forgotpassword

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentForgotPasswordBinding
import com.grupo7.brasilflixapp.databinding.FragmentLoginBinding
import com.grupo7.brasilflixapp.ui.fragments.login.LoginFragment


class ForgotPasswordFragment : Fragment() {

    private var binding: FragmentForgotPasswordBinding? = null
    private lateinit var auth: FirebaseAuth
    private var statusForgotPassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonForgotPassword?.setOnClickListener{
            val email = binding?.forgotEmail?.text.toString()
            if(email.isEmpty()){
                Snackbar.make(
                    this.requireView(),
                    getString(R.string.emptyfields),
                    Snackbar.LENGTH_SHORT
                ).show()
            }else {
                forgotPasswordFirebase(email)
            }
        }

        binding?.backSearch?.setOnClickListener {
            activity?.onBackPressed()
        }

    }

    private fun forgotPasswordFirebase(email: String) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(this.requireActivity()) { task->
            if (task.isSuccessful){
                Log.d(LoginFragment.TAG, "forgotPassword:success")
                findNavController().navigate(R.id.action_forgotPasswordFragment_to_initialFragment)
                Snackbar.make(
                    this.requireView(),
                    getString(R.string.forgotPasswordsuccessfully),
                    Snackbar.LENGTH_SHORT
                ).show()
            }else{
                Log.w(LoginFragment.TAG, "forgotPassword:failure", task.exception)
                Snackbar.make(
                    this.requireView(),
                    getString(R.string.forgotPasswordfailure),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }


}