package com.example.mybookmyshow.RecyclerAdapter


import android.sax.TextElementListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybookmyshow.DataClass.CastCrewData.Cast
import com.example.mybookmyshow.R

class CastAdapter(val castList: List<Cast>):RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }


    class CastViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val castPhoto: ImageView = itemView.findViewById(R.id.ivCastCrewRecyclerMoviePage)
        val castName: TextView = itemView.findViewById(R.id.tvCastCrewNameMoviePage)
        val castJob: TextView = itemView.findViewById(R.id.tvCastCrewJobMoviePage)

        fun bind(dataInput: Cast) {

            castName.text = dataInput.name
            castJob.text = dataInput.known_for_department
            val imagePath = "http://image.tmdb.org/t/p/w500${dataInput.profile_path}"
            if(dataInput.profile_path==null){
                castPhoto.setImageResource(R.drawable.img)
            }
            else {
                Glide.with(itemView.context).load(imagePath).dontAnimate()
                    .into(castPhoto)
            }

        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.castcrew_movie_page_recycler, parent, false)

        return CastViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(castList[position])
    }

    override fun getItemCount(): Int {
        return castList.size
    }
}