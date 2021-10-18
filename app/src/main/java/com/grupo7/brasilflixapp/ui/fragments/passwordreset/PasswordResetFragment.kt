package com.grupo7.brasilflixapp.ui.fragments.passwordreset

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentForgotPasswordBinding
import com.grupo7.brasilflixapp.databinding.FragmentPasswordResetBinding
import com.grupo7.brasilflixapp.extensions.hideKeyboard
import com.grupo7.brasilflixapp.ui.activity.main.MainActivity
import com.grupo7.brasilflixapp.ui.fragments.register.RegisterFragment


class PasswordResetFragment : Fragment() {

    private var binding: FragmentPasswordResetBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordResetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonResetPassword?.setOnClickListener {
            val email1 = binding?.emailField01?.text.toString()
            val email2 = binding?.emailField02?.text.toString()

            if (email1 == email2) {
                this.view?.hideKeyboard()
                changePassword(email1)
            } else {
                binding?.emailResetField1?.error = "Esses e-mails não coincidem. Tentar novamente?"
                binding?.emailResetField2?.error = "Esses e-mails não coincidem. Tentar novamente?"
            }
        }

    }

    private fun changePassword(email: String) {
        val user = auth.currentUser
        if (email == user?.email) {
            auth.apply {
                user.email?.let {
                    sendPasswordResetEmail(it).addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            Log.d(RegisterFragment.TAG, "sendEmailPasswordReset:success")
                            findNavController().navigate(R.id.action_passwordResetFragment_to_confirmationResetEmailFragment)
                        } else {
                            Log.d(RegisterFragment.TAG, "sendEmailPasswordReset:fail")
                            context?.let { it1 ->
                                MaterialAlertDialogBuilder(it1)
                                    .setTitle(resources.getString(R.string.emailResetPasswordConnection))
                                    .setMessage(resources.getString(R.string.tryagainPasswordReset))
                            }?.show()

                        }
                    }
                }
            }
        } else {
            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setTitle(resources.getString(R.string.emailResetPasswordDoesNotMatch))
                    .setMessage(resources.getString(R.string.tryagainPasswordReset))
            }
                ?.show()
        }
    }
}
