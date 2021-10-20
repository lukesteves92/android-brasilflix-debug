package com.grupo7.brasilflixapp.ui.fragments.login


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.registerForActivityResult
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentLoginBinding
import com.grupo7.brasilflixapp.extensions.getUserID
import com.grupo7.brasilflixapp.ui.activity.main.MainActivity
import com.grupo7.brasilflixapp.util.constants.Constants.Login.UserID
import com.grupo7.brasilflixapp.util.constants.Constants.Login.UserName
import com.grupo7.brasilflixapp.util.constants.Constants.Logout.LOGIN_TYPE


class LoginFragment : Fragment() {
    private var binding: FragmentLoginBinding? = null
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    companion object {
        private const val RC_SIGN_IN = 1
        const val TAG = "EmailPassword"
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            UserID = currentUser.uid
            if(currentUser.displayName.isNullOrEmpty()){
                UserName = currentUser.email.toString()
            }else{
                UserName = currentUser.displayName.toString()
            }
            goToPreferences()
            Snackbar.make(
                this.requireView(),
                getString(R.string.alreadyloggedin),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this.requireActivity(), gso)
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
                signInFirebase(email, password)
            }
        }

        binding?.signInButton?.setOnClickListener {
            signInGoogle()
        }

        binding?.forgotPasswordLogin?.setOnClickListener{
            findNavController().navigate(R.id.action_initialFragment_to_forgotPasswordFragment)
        }

    }
    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signInFirebase(mail: String, pass: String) {
        val email = mail
        val password = pass
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val currentUser = auth.currentUser
                    UserID = currentUser?.uid.toString()
                    UserName = currentUser?.email.toString()
                    LOGIN_TYPE = 10
                    goToPreferences()
                    Snackbar.make(
                        this.requireView(),
                        getString(R.string.loginsuccessfully),
                        Snackbar.LENGTH_SHORT
                    ).show()
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val currentUser = auth.currentUser
                    UserID = currentUser?.uid.toString()
                    UserName = currentUser?.displayName.toString()
                    LOGIN_TYPE = 20
                    goToPreferences()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        context,
                        "Erro ao efetuar o login, verificar login/senha",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

    }

    private fun goToPreferences() {
        findNavController().navigate(R.id.action_initialFragment_to_preferences_nav)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}