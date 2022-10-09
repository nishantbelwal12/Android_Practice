package com.example.mybookmyshow.RecyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybookmyshow.R


class PromotionsAdapter(val imagesList:ArrayList<Int>): RecyclerView.Adapter<PromotionsAdapter.PromotionsViewHolder> () {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class PromotionsViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        val imageSrc: ImageView = itemView.findViewById(R.id.ivPromotionsMainActivity)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionsViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.promotions_main_vieewpager, parent, false)
        return PromotionsViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: PromotionsViewHolder, position: Int) {
        val curImage = imagesList[position]
        holder.imageSrc.setImageResource(curImage)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }
}

