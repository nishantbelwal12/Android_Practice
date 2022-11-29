package com.example.mybookmyshow.RecyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybookmyshow.DataClass.UpcomingMovies.Result
import com.example.mybookmyshow.R

class UpcomingMovieAdapter(val moviesList: List<Result>): RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingMoviesViewHolder>() {

    private lateinit var mListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class UpcomingMoviesViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        val moviePoster: ImageView = itemView.findViewById(R.id.ivUpcomingMoviePoster)
        val movieName: TextView = itemView.findViewById(R.id.tvUpcomingMovieName)
        val movieReleaseDate: TextView = itemView.findViewById(R.id.tvUpcomingMovieReleaseDate)

        fun bind(dataInput: Result){

            movieName.text = dataInput.original_title
            movieReleaseDate.text = "Releasing on ${dataInput.release_date.toString()}"

            val imagePath = "http://image.tmdb.org/t/p/w500${dataInput.poster_path.toString()}"
            if(dataInput.poster_path==null){
                moviePoster.setImageResource(R.drawable.img)
            }
            else{
                Glide.with(itemView.context).load(imagePath).dontAnimate().into(moviePoster)
            }

        }

        init{
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMoviesViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.upcoming_movie_recycler,parent,false)
        return UpcomingMoviesViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {

        holder.bind(moviesList[position])


    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

}
