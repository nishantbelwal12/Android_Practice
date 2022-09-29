package com.example.bookmyshowclone.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookmyshowclone.Castdata.Cast
import com.example.bookmyshowclone.R
import com.example.bookmyshowclone.Top_rated_Data.Result

class CastAdapter(val castList: List<Cast>):RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private lateinit var mListener:onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    class CastViewHolder(itemView: View, listener: onItemClickListener):RecyclerView.ViewHolder(itemView){
        val castPhoto: ImageView = itemView.findViewById(R.id.ivCastPoster)
        val castName: TextView = itemView.findViewById(R.id.tvCastNameItem)

        fun bind(dataInput: Cast){

            castName.text = dataInput.name
            val imagePath = "http://image.tmdb.org/t/p/w500${dataInput.profile_path}"
            Glide.with(itemView.context).load(imagePath).dontAnimate().into(castPhoto)
        }

        init{
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cast_layout,parent,false)

        return CastViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(castList[position])
    }

    override fun getItemCount(): Int {
        return castList.size
    }
}