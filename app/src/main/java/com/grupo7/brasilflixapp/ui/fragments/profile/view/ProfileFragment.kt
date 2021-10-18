package com.grupo7.brasilflixapp.ui.fragments.profile.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FragmentProfileBinding
import com.grupo7.brasilflixapp.extensions.getUserID
import com.grupo7.brasilflixapp.ui.model.profile.ItemProfile
import com.grupo7.brasilflixapp.util.constants.Constants
import com.grupo7.brasilflixapp.util.constants.Constants.Login.UserID
import com.grupo7.brasilflixapp.util.constants.Constants.Logout.LOGIN_TYPE
import com.grupo7.brasilflixapp.util.constants.Constants.Profile.GOOGLE_ACCOUNT_URL
import com.grupo7.brasilflixapp.util.enumarators.ProfileItemActionEnum
import com.grupo7.brasilflixapp.util.interfaces.IProfileItemClick
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

const val GET_IMAGE_REQUEST = 1

class profileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.changePictureProfile?.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, GET_IMAGE_REQUEST)
        }

        loadProfileImageFromStorage()



        binding?.cvProfileItemContainer?.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_passwordResetFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onActivityResult(request: Int, code: Int, intent: Intent?) {
        super.onActivityResult(request, code, intent)
        if (code == AppCompatActivity.RESULT_OK && request == GET_IMAGE_REQUEST && intent?.data != null) {
            val imageURI = intent.data!!

            imageURI.run {
                binding?.pictureCardProfile?.setImageURI(this)
                val firebase = FirebaseStorage.getInstance()
                val storage = firebase.getReference("UserProfileImages")
                val fileReference = storage.child("${UserID}.jpeg")
                fileReference.putFile(this)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Upload com sucesso!", Toast.LENGTH_SHORT).show()

                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Upload falhou!", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun loadProfileImageFromStorage() {
        val firebase = FirebaseStorage.getInstance()
        val storage = firebase.getReference("UserProfileImages")
        storage.child("${UserID}.jpeg").downloadUrl.addOnSuccessListener {
            Picasso.get()
                .load(it.toString())
                .error(R.drawable.nophoto)
                .into(binding?.pictureCardProfile)

        }
    }


}