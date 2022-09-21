package com.example.bookmyshow.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyshow.DataClasses.BestSellerItem
import com.example.bookmyshow.R

class BestSellerAdapter(private val newList:ArrayList<BestSellerItem>):RecyclerView.Adapter<BestSellerAdapter.MyBestSellerViewAdapter>() {


    class MyBestSellerViewAdapter(itemView: View): RecyclerView.ViewHolder(itemView){
        val moviePoster:ImageView = itemView.findViewById(R.id.ivMoviePoster)
        val movieName:TextView = itemView.findViewById(R.id.tvMovieName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBestSellerViewAdapter {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.best_seller_layout,parent,false)
        return MyBestSellerViewAdapter(itemView)
    }

    override fun onBindViewHolder(holder: MyBestSellerViewAdapter, position: Int) {
        val currentItem = newList[position]
        holder.moviePoster.setImageResource(currentItem.poster)
        holder.movieName.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return newList.size
    }
}