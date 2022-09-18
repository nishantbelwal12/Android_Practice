package com.example.recycler4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyHolder(private val newList: ArrayList<DataItem>):RecyclerView.Adapter<MyHolder.MyViewHolder>() {

    private lateinit var mListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    fun deleteItem(position:Int){
        newList.removeAt(position)
        notifyDataSetChanged()
    }

    fun archived(position: Int,item: DataItem){
        newList.add(position,item)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View,listener: onItemClickListener):RecyclerView.ViewHolder(itemView){

        var name:TextView = itemView.findViewById(R.id.tvName)
        var dob:TextView = itemView.findViewById(R.id.tvDOB)
        init{
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_view,parent,false)

        return MyViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = newList[position]
        holder.name.text = currentItem.name
        holder.dob.text = currentItem.dob
    }

    override fun getItemCount(): Int {
        return newList.size
    }
}