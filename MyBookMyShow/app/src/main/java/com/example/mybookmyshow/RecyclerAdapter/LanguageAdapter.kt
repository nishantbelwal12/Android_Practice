package com.example.mybookmyshow.RecyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybookmyshow.R


class LanguageAdapter(private val languageList:ArrayList<String>): RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    private lateinit var mListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class LanguageViewHolder(itemView: View, listener: onItemClickListener):RecyclerView.ViewHolder(itemView) {

        val language: TextView = itemView.findViewById(R.id.tvLanguageRecyclerMainActivity)
        init{
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.language_main_recycler,parent,false)
        return LanguageViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val currentLanguage = languageList[position]
        holder.language.text = currentLanguage
    }

    override fun getItemCount(): Int {
        return languageList.size
    }
}