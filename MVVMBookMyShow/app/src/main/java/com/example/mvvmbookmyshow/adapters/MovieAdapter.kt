package com.example.mvvmbookmyshow.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmbookmyshow.R
import com.example.mvvmbookmyshow.models.MovieData.MovieDataApiResponse
import kotlinx.android.synthetic.main.movie_page.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MovieAdapter:RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    @RequiresApi(Build.VERSION_CODES.O)
    val current = LocalDateTime.now()

    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    @RequiresApi(Build.VERSION_CODES.O)
    val formatted = current.format(formatter)

    private val differCallback = object : DiffUtil.ItemCallback<MovieDataApiResponse>(){
        override fun areItemsTheSame(
            oldItem: MovieDataApiResponse,
            newItem: MovieDataApiResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieDataApiResponse,
            newItem: MovieDataApiResponse
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_page,parent,false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        val imagePath = "http://image.tmdb.org/t/p/w500${movie.poster_path}"
        holder.itemView.apply {
            Glide.with(this).load(imagePath).into(ivMoviePosterMoviePage)

            if(movie.release_date.compareTo(formatted)>0){
                tvReleaseDateMoviePage.text = "Releasing on ${movie.release_date}"
            }
            else{
                tvReleaseDateMoviePage.text = "Released on ${movie.release_date}"
            }

            tvDurationMoviePage.text = "${movie.runtime.toString()} min"
            tvAboutMoviePage.text = movie.overview
            tvLikesMoviePage.text = movie.popularity.toInt().toString()
            setOnClickListener{
                onItemClickListener?.let { it(movie) }
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((MovieDataApiResponse)->Unit)?=null

    fun setOnItemClickListener(listener: (MovieDataApiResponse)->Unit){
        onItemClickListener = listener
    }

}