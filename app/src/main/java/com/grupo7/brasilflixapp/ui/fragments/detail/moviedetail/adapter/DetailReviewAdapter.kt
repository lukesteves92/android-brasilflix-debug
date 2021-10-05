package com.grupo7.brasilflixapp.ui.fragments.detail.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.ReviewsBinding
import com.grupo7.brasilflixapp.ui.model.reviews.AuthorResults

class DetailReviewAdapter (
    private val reviewsList: List<AuthorResults>,
) : RecyclerView.Adapter<DetailReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ReviewsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reviewsList[position])
    }

    override fun getItemCount() = reviewsList.size

    class ViewHolder(
        val binding: ReviewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(
            Result: AuthorResults,
        ) = with(binding) {
            Result?.let {
                Glide.with(itemView)
                    .load(Result.author_details.avatar_path)
                    .placeholder(R.drawable.brflixlogo)
                    .into(reviewImage)
                reviewTitle.text = Result.author_details.name
                reviewRating.text = Result.author_details.rating.toString()

            }
        }
    }
}