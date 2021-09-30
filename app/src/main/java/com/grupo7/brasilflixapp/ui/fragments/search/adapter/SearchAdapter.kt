package com.grupo7.brasilflixapp.ui.fragments.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.SearchBinding
import com.grupo7.brasilflixapp.ui.model.films.films

class searchAdapter (
    private val filmsList: List<films>,
    private val onClickListener: (films: films) -> Unit
) : RecyclerView.Adapter<searchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filmsList[position], onClickListener)
    }
    override fun getItemCount() = filmsList.size

    class ViewHolder(
        val binding: SearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            films: films,
            onClickListener: (films: films) -> Unit,
        ) = with(binding) {
            films.let {
                Glide.with(itemView)
                    .load(films.poster_path)
                    .placeholder(R.drawable.films)
                    .into(fotoFilme)
                filmeName.text = films.title
                dataLancamento.text = (films.release_date)
                voteModelText.text = films.vote_average.toString()
                searchContainer.setOnClickListener{
                    onClickListener(films)
                }
            }
        }
    }
}
