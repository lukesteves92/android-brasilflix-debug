package com.grupo7.brasilflixapp.ui.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FilmsBinding
import com.grupo7.brasilflixapp.ui.model.films.films

class upcomingAdapter (
    private val onClickListener: (films: films) -> Unit
) : PagedListAdapter<films, upcomingAdapter.ViewHolder>(films.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FilmsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onClickListener) }
    }

    class ViewHolder(
        val binding: FilmsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            films: films,
            onClickListener: (films: films) -> Unit,
        ) = with(binding) {
            films?.let {
                Glide.with(itemView)
                    .load(films.poster_path)
                    .placeholder(R.drawable.films)
                    .into(fotoFilme)
                filmeName.text = films.title
                dataLancamento.text = films.release_date
                voteModelText.text = films.vote_average.toString()
                filmesContainer.setOnClickListener{
                    onClickListener(films)
                }
            }
        }
    }
}
