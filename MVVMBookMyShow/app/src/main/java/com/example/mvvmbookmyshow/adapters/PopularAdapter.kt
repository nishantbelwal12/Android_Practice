package com.example.mvvmbookmyshow.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmbookmyshow.R
import com.example.mvvmbookmyshow.models.PopularMovie.Result
import kotlinx.android.synthetic.main.popular.view.*

class PopularAdapter:RecyclerView.Adapter<PopularAdapter.UpcomingViewHolder>() {

    inner class UpcomingViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

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
            LayoutInflater.from(parent.context).inflate(R.layout.popular,parent,false)
        )
    }


    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val movie = differ.currentList[position]
        val imagePath = "http://image.tmdb.org/t/p/w500${movie.poster_path}"
        holder.itemView.apply {
            Glide.with(this).load(imagePath).into(ivMoviePosterRecyclerMoviePage)

            tvMovieNameRecyclerMoviePage.text = movie.original_title

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