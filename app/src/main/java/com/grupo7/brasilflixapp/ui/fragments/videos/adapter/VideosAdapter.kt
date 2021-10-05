package com.grupo7.brasilflixapp.ui.fragments.videos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FilmsBinding
import com.grupo7.brasilflixapp.databinding.VideosBinding
import com.grupo7.brasilflixapp.ui.fragments.home.adapter.upcomingAdapter
import com.grupo7.brasilflixapp.ui.model.films.films
import com.grupo7.brasilflixapp.ui.model.videos.Videos

class VideosAdapter (
    private val videosList: List<Videos>,
    private val onClickListener: (videos: Videos) -> Unit
) : RecyclerView.Adapter<VideosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = VideosBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(videosList[position], onClickListener)
    }

    override fun getItemCount() = videosList.size

    class ViewHolder(
        val binding: VideosBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            videos: Videos,
            onClickListener: (videos: Videos) -> Unit
        ) {
            binding.tvWatchTitle.text = videos.name.toString()
            binding.watchVideoCard.setOnClickListener{
                onClickListener(videos)
            }

        }
    }
}