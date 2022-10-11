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
import com.example.mvvmbookmyshow.models.CastCrew.Cast
import com.example.mvvmbookmyshow.models.CastCrew.CastCrewApiResponse
import com.example.mvvmbookmyshow.models.MovieData.MovieDataApiResponse
import kotlinx.android.synthetic.main.castcrew.view.*
import kotlinx.android.synthetic.main.movie_page.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CastAdapter:RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    inner class CastViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Cast>(){
        override fun areItemsTheSame(
            oldItem: Cast,
            newItem: Cast
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Cast,
            newItem: Cast
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.castcrew,parent,false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = differ.currentList[position]
        val imagePath = "http://image.tmdb.org/t/p/w500${cast.profile_path}"
        holder.itemView.apply {
            Glide.with(this).load(imagePath).into(ivCastCrewRecyclerMoviePage)
            tvCastCrewNameMoviePage.text = cast.name
            tvCastCrewJobMoviePage.text = cast.character


            setOnClickListener{
                onItemClickListener?.let { it(cast) }
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Cast)->Unit)?=null

    fun setOnItemClickListener(listener: (Cast)->Unit){
        onItemClickListener = listener
    }

}