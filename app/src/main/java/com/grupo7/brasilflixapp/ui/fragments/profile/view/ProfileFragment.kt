package com.grupo7.brasilflixapp.ui.fragments.profile.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo7.brasilflixapp.ui.fragments.profile.adapter.ProfileItemAdapter
import com.grupo7.brasilflixapp.databinding.FragmentProfileBinding
import com.grupo7.brasilflixapp.model.profile.ItemProfile
import com.grupo7.brasilflixapp.util.enumarators.ProfileItemActionEnum
import com.grupo7.brasilflixapp.util.interfaces.IProfileItemClick


class profileFragment : Fragment(), IProfileItemClick {

    private var binding: FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item1 = ItemProfile("Conta", "Alterar senha, email, foto...", true, ProfileItemActionEnum.GO_TO_EDIT_ACCOUNT)
        val item2 = ItemProfile("Economia de dados", "", false, ProfileItemActionEnum.NO_ACTION)
        val item3 = ItemProfile("Modo offline", "", false, ProfileItemActionEnum.NO_ACTION)
        val item4 = ItemProfile("Notificações", "Habilitar para receber notificações de push", false, ProfileItemActionEnum.NO_ACTION)

        val list = listOf<ItemProfile>(item1, item2, item3, item4)
        val itemAdapter = ProfileItemAdapter(list, this) {
            Log.i("item", it.itemTitle)
        }

        binding?.let {
            with(it) {
                rvProfileItemList.layoutManager = LinearLayoutManager(context)
                rvProfileItemList.adapter = itemAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    override fun itemProfileCLick(item: ItemProfile) {
        TODO("Not yet implemented")
    }

}