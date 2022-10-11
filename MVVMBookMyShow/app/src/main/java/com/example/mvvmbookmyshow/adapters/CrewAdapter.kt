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
import com.example.mvvmbookmyshow.models.CastCrew.Crew
import com.example.mvvmbookmyshow.models.CastCrew.CastCrewApiResponse
import com.example.mvvmbookmyshow.models.MovieData.MovieDataApiResponse
import kotlinx.android.synthetic.main.castcrew.view.*
import kotlinx.android.synthetic.main.movie_page.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CrewAdapter:RecyclerView.Adapter<CrewAdapter.CrewViewHolder>() {

    inner class CrewViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Crew>(){
        override fun areItemsTheSame(
            oldItem: Crew,
            newItem: Crew
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Crew,
            newItem: Crew
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        return CrewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.castcrew,parent,false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        val crew = differ.currentList[position]
        val imagePath = "http://image.tmdb.org/t/p/w500${crew.profile_path}"
        holder.itemView.apply {
            Glide.with(this).load(imagePath).into(ivCastCrewRecyclerMoviePage)
            tvCastCrewNameMoviePage.text = crew.name
            tvCastCrewJobMoviePage.text = crew.job


            setOnClickListener{
                onItemClickListener?.let { it(crew) }
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Crew)->Unit)?=null

    fun setOnItemClickListener(listener: (Crew)->Unit){
        onItemClickListener = listener
    }

}