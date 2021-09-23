package com.grupo7.brasilflixapp.ui.fragments.profile.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupo7.brasilflixapp.databinding.ProfileClickableItemBinding
import com.grupo7.brasilflixapp.databinding.ProfileNonClickableItemBinding
import com.grupo7.brasilflixapp.model.profile.ItemProfile
import com.grupo7.brasilflixapp.util.enumarators.ProfileItemActionEnum
import com.grupo7.brasilflixapp.util.interfaces.IProfileItemClick


class ProfileItemAdapter(
    private val items: List<ItemProfile>,
    private val profileItemClick: IProfileItemClick,
    private val itemClick: (item: ItemProfile) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ClickableViewHolder(val binding: ProfileClickableItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: ItemProfile,
            profileItemClick: IProfileItemClick,
            itemClick: (item: ItemProfile) -> Unit
        ) {
            binding.tvItemTitle.text = item.itemTitle
            binding.tvItemDescription.text = item.itemDescription


            when (item.action) {
                ProfileItemActionEnum.GO_TO_EDIT_ACCOUNT -> {
                    binding.cvProfileItemContainer.setOnClickListener {
//                        binding.root.findNavController().navigate(R.id.action_profileFragment_to_accountFragment)
                    }
                }
                else -> return
            }

        }
    }

    class NonClickableViewHolder(val binding: ProfileNonClickableItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: ItemProfile,
        ) {
            binding.tvItemTitle.text = item.itemTitle
            binding.tvItemDescription.text = item.itemDescription
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == CLICKABLE) {
            val binding = ProfileClickableItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            ClickableViewHolder(binding)
        } else {
            val binding = ProfileNonClickableItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            NonClickableViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ClickableViewHolder) {
            holder.bind(items[position], profileItemClick, itemClick)
        } else if (holder is NonClickableViewHolder) {
            holder.bind(items[position])
        }

    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position].isClickable) {
            true -> CLICKABLE
            else -> NON_CLICKABLE
        }
    }

    companion object {
        val CLICKABLE = 1
        val NON_CLICKABLE = 2
    }

}