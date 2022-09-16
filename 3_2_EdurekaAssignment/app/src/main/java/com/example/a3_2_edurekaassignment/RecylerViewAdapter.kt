package com.example.a3_2_edurekaassignment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class RecylerViewAdapter(
    private val tasks: List<Task>
): RecyclerView.Adapter<RecylerViewAdapter.TaskViewHolder>(){

    class TaskViewHolder(itemView: View,):RecyclerView.ViewHolder(itemView){
        var button: Button = itemView.findViewById(R.id.button)
        var bgColor: FrameLayout = itemView.findViewById(R.id.flrightt)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.buttonview,parent,false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        var currentItem = tasks[position]
        holder.button.text = currentItem.buttonName
        holder.bgColor.setBackgroundColor(currentItem.color)

    }
}