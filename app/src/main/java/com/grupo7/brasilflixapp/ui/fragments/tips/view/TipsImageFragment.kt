package com.grupo7.brasilflixapp.ui.fragments.tips.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.grupo7.brasilflixapp.R

const val PARAM_NUMBER = "param_number"

class TipsImageFragment : Fragment() {
    private var number: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            number = it.getInt(PARAM_NUMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tips_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(number){
            0 -> view.findViewById<ImageView>(R.id.imgImageTab).setImageDrawable(
                context?.let { ContextCompat.getDrawable(it, R.drawable.tipsimageone) })
            1 -> view.findViewById<ImageView>(R.id.imgImageTab).setImageDrawable(
                context?.let { ContextCompat.getDrawable(it, R.drawable.tipsimagetwo) })

        }
    }

    companion object {
        @JvmStatic fun newInstance(param1: Int) =
            TipsImageFragment().apply {
                arguments = Bundle().apply {
                    putInt(PARAM_NUMBER, param1)
                }
            }
    }


}