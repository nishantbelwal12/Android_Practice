package com.example.mybookmyshow.RecyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybookmyshow.DataClass.TopRated.Result
import com.example.mybookmyshow.R


class MoviesMainAdapter(val moviesList: List<Result>): RecyclerView.Adapter<MoviesMainAdapter.MoviesViewHolder>() {

    private lateinit var mListener:onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class MoviesViewHolder(itemView: View, listener: onItemClickListener):RecyclerView.ViewHolder(itemView) {
        val movieTitle: ImageView = itemView.findViewById(R.id.ivMoviePosterRecyclerMain)
        val movieName: TextView = itemView.findViewById(R.id.tvMovieNameRecyclerMain)
        val movieRating: TextView = itemView.findViewById(R.id.tvMovieLikesRecyclerMain)

        fun bind(dataInput: Result){

            movieName.text = dataInput.original_title
            movieRating.text = dataInput.popularity.toString()
            val imagePath = "http://image.tmdb.org/t/p/w500${dataInput.poster_path}"
            Glide.with(itemView.context).load(imagePath).dontAnimate().into(movieTitle)

        }

        init{
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_main_recycler,parent,false)
        return MoviesViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        holder.bind(moviesList[position])


    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}