package com.example.a3_2_edureka

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val newArrayList:ArrayList<DataItem>):
        RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private lateinit var mListener:onItemCLickListener
    interface onItemCLickListener{
        fun onItemClick(position:Int)
    }
    fun setOnItemClickListener(listener:onItemCLickListener){
        mListener=listener
    }

    class MyViewHolder(itemView: View, listener: onItemCLickListener):RecyclerView.ViewHolder(itemView){

        val button: Button = itemView.findViewById(R.id.btnListView)
        init{
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return MyViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newArrayList[position]
        holder.button.text = currentItem.button
    }

    override fun getItemCount(): Int {
        return newArrayList.size
    }
}