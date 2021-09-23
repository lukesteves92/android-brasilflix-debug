package com.grupo7.brasilflixapp.ui.fragments.login

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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var binding: FragmentLoginBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.floatingActionButton?.setOnClickListener {

            val email = binding?.loginEmail?.text.toString()
            val password = binding?.loginSenha?.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Snackbar.make(
                    this.requireView(),
                    getString(R.string.emptyfields),
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                signIn(email, password)

            }
//            val bundle = Bundle()
//            with(bundle){
//                putString(KEY_USER, email)
//                putString(KEY_PASSWORD, password)
//            }

//            startActivity(Intent(activity, homeActivity::class.java), bundle)

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun signIn(mail: String, pass: String) {
        val email = mail
        val password = pass
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    findNavController().navigate(R.id.action_initialFragment_to_tips_nav)
                    Snackbar.make(
                        this.requireView(),
                        getString(R.string.loginsuccessfully),
                        Snackbar.LENGTH_SHORT
                    ).show()
//               startActivity(Intent(activity, MainActivity::class.java))
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        context,
                        "Erro ao efetuar o login, verificar login/senha",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }

    }

    private fun updateUI(user: FirebaseUser?) {

    }

    override fun onStart() {
        super.onStart()
//        var email = ""
//        val currentUser = auth.currentUser
//        if(currentUser != null){
//            currentUser.let{
//                var email = currentUser.uid
//            }
//            val bundle = Bundle()
//            with(bundle){
//                putString(KEY_USER, email)
//
//            }
//            Snackbar.make(
//                this.requireView(),
//                getString(R.string.alreadyloggedin),
//                Snackbar.LENGTH_SHORT
//            ).show()
//            findNavController().navigate(R.id.action_inicialFragment_to_home_nav, bundle)
//        }
    }

    private fun reload() {

    }

    companion object {
        private const val TAG = "EmailPassword"
        private const val TAG2 = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
        const val KEY_USER = "user"
        const val KEY_PASSWORD = "password"
    }

    private fun signOut() {
        Firebase.auth.signOut()

    }
}