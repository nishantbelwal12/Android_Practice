package com.example.mybookmyshow.RecyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybookmyshow.R
import com.example.mybookmyshow.DataClass.TopRated.Result


class SimilarMoviesAdapter(val moviesList: List<Result>): RecyclerView.Adapter<SimilarMoviesAdapter.SimilarMoviesViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class SimilarMoviesViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val movieTitle: ImageView = itemView.findViewById(R.id.ivMoviePosterRecyclerMoviePage)
        val movieName: TextView = itemView.findViewById(R.id.tvMovieNameRecyclerMoviePage)
        val movieRating: TextView = itemView.findViewById(R.id.tvMovieLikesRecyclerMoviePage)

        fun bind(dataInput: Result) {

            movieName.text = dataInput.original_title
            movieRating.text = dataInput.popularity.toString()
            val imagePath = "http://image.tmdb.org/t/p/w500${dataInput.poster_path}"
            Glide.with(itemView.context).load(imagePath).dontAnimate().into(movieTitle)
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_movie_page_recycler, parent, false)
        return SimilarMoviesViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: SimilarMoviesViewHolder, position: Int) {

        holder.bind(moviesList[position])


    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}