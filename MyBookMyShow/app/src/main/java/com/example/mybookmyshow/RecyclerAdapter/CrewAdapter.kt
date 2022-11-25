package com.example.mybookmyshow.RecyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybookmyshow.DataClass.CastCrewData.Crew
import com.example.mybookmyshow.R


class CrewAdapter(val crewList: List<Crew>): RecyclerView.Adapter<CrewAdapter.CrewViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }


    class CrewViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val crewPhoto: ImageView = itemView.findViewById(R.id.ivCastCrewRecyclerMoviePage)
        val crewName: TextView = itemView.findViewById(R.id.tvCastCrewNameMoviePage)
        val crewJob: TextView = itemView.findViewById(R.id.tvCastCrewJobMoviePage)

        fun bind(dataInput: Crew) {

            crewName.text = dataInput.name
            crewJob.text = dataInput.known_for_department
            val imagePath = "http://image.tmdb.org/t/p/w500${dataInput.profile_path}"
            if(dataInput.profile_path==null){
                crewPhoto.setImageResource(R.drawable.img)
            }
            else {
                Glide.with(itemView.context).load(imagePath).dontAnimate()
                    .into(crewPhoto)
            }
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.castcrew_movie_page_recycler, parent, false)

        return CrewViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        holder.bind(crewList[position])
    }

    override fun getItemCount(): Int {
        return crewList.size
    }
}