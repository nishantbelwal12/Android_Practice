package com.example.bookmyshowclone.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyshowclone.R

class LanguageAdapter(private val languageList:ArrayList<String>):RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    private lateinit var mListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class LanguageViewHolder(itemView: View, listener: onItemClickListener):RecyclerView.ViewHolder(itemView) {

        val language:TextView = itemView.findViewById(R.id.tvMainLanguagesCard)
        init{
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_lanuages_item_layout,parent,false)
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