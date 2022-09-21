package com.example.bookmyshow.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyshow.DataClasses.OfferItem
import com.example.bookmyshow.R

class OfferLayoutAdapter(private val newList: ArrayList<OfferItem>): RecyclerView.Adapter<OfferLayoutAdapter.MyOfferViewHolder>(){


    class MyOfferViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val offerItem: ImageView = itemView.findViewById(R.id.ivOffer)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOfferViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.offer_layout,parent,false)

        return MyOfferViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyOfferViewHolder, position: Int) {
        val currentItem = newList[position]
        holder.offerItem.setImageResource(currentItem.offer)
    }

    override fun getItemCount(): Int {
        return newList.size
    }
}