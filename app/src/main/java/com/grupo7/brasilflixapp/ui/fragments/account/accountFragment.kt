package com.grupo7.brasilflixapp.ui.fragments.account

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo7.brasilflixapp.ui.fragments.profile.adapter.ProfileItemAdapter
import com.grupo7.brasilflixapp.databinding.FragmentAccountBinding
import com.grupo7.brasilflixapp.model.profile.ItemProfile
import com.grupo7.brasilflixapp.util.enumarators.ProfileItemActionEnum
import com.grupo7.brasilflixapp.util.interfaces.IProfileItemClick


class accountFragment : Fragment(), IProfileItemClick {

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
        val item1 = ItemProfile("Alterar Senha", "", true, ProfileItemActionEnum.GO_TO_EDIT_ACCOUNT)
        val item2 = ItemProfile("Alterar foto do perfil", "", true, ProfileItemActionEnum.NO_ACTION)
        val item3 = ItemProfile("Alterar email", "", true, ProfileItemActionEnum.NO_ACTION)
        val item4 = ItemProfile("Cancelar conta", "", true, ProfileItemActionEnum.NO_ACTION)

        val list = listOf<ItemProfile>(item1, item2, item3, item4)
        val itemAdapter = ProfileItemAdapter(list, this) {
            Log.i("item", it.itemTitle)
        }

        binding?.let {
            with(it) {
                rvAccountItemList.layoutManager = LinearLayoutManager(context)
                rvAccountItemList.adapter = itemAdapter
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