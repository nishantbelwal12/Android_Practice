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
import com.example.mvvmbookmyshow.models.UpcomingMovie.Result
import kotlinx.android.synthetic.main.upcoming.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UpcomingAdapter:RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {

    inner class UpcomingViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    @RequiresApi(Build.VERSION_CODES.O)
    val current = LocalDateTime.now()

    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    @RequiresApi(Build.VERSION_CODES.O)
    val formatted = current.format(formatter)

    private val differCallback = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        return UpcomingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.upcoming,parent,false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val movie = differ.currentList[position]
        val imagePath = "http://image.tmdb.org/t/p/w500${movie.poster_path}"
        holder.itemView.apply {
            Glide.with(this).load(imagePath).into(ivUpcomingMoviePoster)

            if(movie.release_date.compareTo(formatted)>0){
                tvUpcomingMovieReleaseDate.text = "Releasing on ${movie.release_date}"
            }
            else{
                tvUpcomingMovieReleaseDate.text = "Released on ${movie.release_date}"
            }

            tvUpcomingMovieName.text = movie.original_title

            setOnClickListener{
                onItemClickListener?.let { it(movie) }
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Result)->Unit)?=null

    fun setOnItemClickListener(listener: (Result)->Unit){
        onItemClickListener = listener
    }

}