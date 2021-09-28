package com.grupo7.brasilflixapp.ui.fragments.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.database.favorites.model.Favorites
import com.grupo7.brasilflixapp.database.favorites.model.FavoritesSeries
import com.grupo7.brasilflixapp.databinding.FavoritesBinding

class FavoritesSeriesAdapter (
    private val filmsList: List<FavoritesSeries>,
    private val onClickListener: (movie: FavoritesSeries) -> Unit
) : RecyclerView.Adapter<FavoritesSeriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FavoritesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filmsList[position], onClickListener)
    }
    override fun getItemCount() = filmsList.size

    class ViewHolder(
        val binding: FavoritesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            films: FavoritesSeries,
            onClickListener: (movie: FavoritesSeries) -> Unit,
        ) = with(binding) {
            films.let {
                Glide.with(itemView)
                    .load(films.poster_path)
                    .placeholder(R.drawable.films)
                    .into(fotoFilme)
                filmeName.text = films.original_name
                removeFavorite.setOnClickListener{
                    onClickListener(films)
                }

            }
        }
    }
}