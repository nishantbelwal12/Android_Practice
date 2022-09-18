package com.example.recycler4

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class SwipeGesture(context:Context):ItemTouchHelper.SimpleCallback(
    0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {


    val deleteColor = ContextCompat.getColor(context,R.color.red)
    val archivecolor = ContextCompat.getColor(context,R.color.green)
    val deleteIcon = R.drawable.ic_baseline_delete_sweep_24
    val archieve = R.drawable.ic_baseline_archive_24

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {

        return false
    }

//    override fun onChildDraw(
//        c: Canvas,
//        recyclerView: RecyclerView,
//        viewHolder: RecyclerView.ViewHolder,
//        dX: Float,
//        dY: Float,
//        actionState: Int,
//        isCurrentlyActive: Boolean
//    ) {
//
//        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//    }






}