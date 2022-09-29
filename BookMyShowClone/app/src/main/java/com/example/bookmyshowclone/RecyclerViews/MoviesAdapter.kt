package com.example.bookmyshowclone.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookmyshowclone.R
import com.example.bookmyshowclone.Top_rated_Data.Result

class MoviesAdapter(val moviesList: List<Result>):RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private lateinit var mListener:onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class MoviesViewHolder(itemView: View,listener: onItemClickListener):RecyclerView.ViewHolder(itemView) {
        val movieTitle: ImageView = itemView.findViewById(R.id.ivRecyclerItemMovie)
        val movieName: TextView = itemView.findViewById(R.id.tvRecyclerMovie)
        val movieRating: TextView = itemView.findViewById(R.id.tvRecyclerRatings)

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
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_movies_item_layout,parent,false)
        return MoviesViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        holder.bind(moviesList[position])


    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}