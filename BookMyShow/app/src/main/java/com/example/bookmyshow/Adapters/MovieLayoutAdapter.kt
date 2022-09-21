package com.example.bookmyshow.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyshow.DataClasses.MovieItem
import com.example.bookmyshow.R

class MovieLayoutAdapter(private val newList:ArrayList<MovieItem>):RecyclerView.Adapter<MovieLayoutAdapter.MyMovieViewHolder>() {

    private lateinit var movieListener: onMovieItemClickListener
    interface onMovieItemClickListener{
        fun onMovieClick(position: Int)
    }

    fun setonMovieClickListener(listener: onMovieItemClickListener){
        movieListener = listener
    }

    class MyMovieViewHolder(itemView: View,listener: onMovieItemClickListener):RecyclerView.ViewHolder(itemView) {

        val moviePoster:ImageView = itemView.findViewById(R.id.ivMovieLayoutPoster)
        val movieName:TextView = itemView.findViewById(R.id.tvMovieLayoutName)
        init{
            itemView.setOnClickListener{
                listener.onMovieClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_layout,parent,false)
        return MyMovieViewHolder(itemView,movieListener)
    }

    override fun onBindViewHolder(holder: MyMovieViewHolder, position: Int) {

        val currentItem = newList[position]
        holder.moviePoster.setImageResource(currentItem.poster)
        holder.movieName.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return newList.size
    }
}