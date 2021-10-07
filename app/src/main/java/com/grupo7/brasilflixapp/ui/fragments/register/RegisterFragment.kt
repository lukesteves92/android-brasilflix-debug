package com.grupo7.brasilflixapp.ui.fragments.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentRegisterBinding
import com.grupo7.brasilflixapp.util.cpf.CPFUtil
import com.grupo7.brasilflixapp.util.mask.Mask


class RegisterFragment : Fragment() {
    private var binding: FragmentRegisterBinding? = null
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()

//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            reload();
//        }
    }

//    private fun reload() {
//
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.campoCpf?.addTextChangedListener(binding?.campoCpf?.let {
            Mask.mask(
                "###.###.###-##",
                it
            )
        })

        binding?.campoTelefone?.addTextChangedListener(binding?.campoTelefone?.let {
            Mask.mask(
                "(##) #####-####",
                it
            )
        })

        binding?.buttonCadastrar?.setOnClickListener {
            if (CPFUtil.myValidateCPF(binding?.campoCpf?.text.toString())) {
                val email = binding?.campoEmail?.text.toString()
                val password = binding?.campoSenha?.text.toString()
                registerUser(email, password)
                findNavController().navigate(R.id.action_initialFragment_to_confirmationRegisterFragment)
                Snackbar.make(
                    this.requireView(),
                    getString(R.string.cadastrar2),
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                binding?.layoutCpf?.error = "CPF inválido ou informações incorretas"
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    companion object {
        const val TAG = "EmailPassword"

    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    updateUI(null)
                }
            }
    }
}