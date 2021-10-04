package com.grupo7.brasilflixapp.ui.fragments.account

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentAccountBinding
import com.grupo7.brasilflixapp.ui.activity.main.MainActivity
import com.grupo7.brasilflixapp.util.constants.Constants
import com.grupo7.brasilflixapp.util.constants.Constants.Logout.LOGIN_TYPE


class accountFragment : Fragment() {

    private var binding: FragmentAccountBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonLogout?.setOnClickListener{
            if(LOGIN_TYPE == 10) {
                Firebase.auth.signOut()
                startActivity(Intent(activity, MainActivity::class.java))
                onDestroyView()
            } else if (LOGIN_TYPE == 20){
                Firebase.auth.signOut()
                logoutGoogle()
                startActivity(Intent(activity, MainActivity::class.java))
                onDestroyView()
            }
        }

    }
    private fun logoutGoogle() {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        GoogleSignIn.getClient(this.requireActivity(), gso).signOut()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }




}