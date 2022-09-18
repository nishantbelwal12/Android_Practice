package com.example.recycler5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity() : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<DataItem>
    lateinit var name: Array<String>
    lateinit var dob: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = arrayOf(
            "Nishant",
            "Khushi",
            "Muskan",
            "Abhinandan",
            "Nitik",
            "Shayak",
            "Anjum",
            "Vanshika",
            "Spoorti",
            "Rajendran",
            "Vishwas",
            "Arwind"
        )

        dob = arrayOf(
            "19-jan",
            "12-Sep",
            "18-jan",
            "24-jan",
            "30-feb",
            "31-feb",
            "31-apr",
            "13-apr",
            "31-june",
            "12-aug",
            "23-oct",
            "16-dec"
        )

        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<DataItem>()
        getUserdata()
    }

    private fun getUserdata() {
        for (i in name.indices) {
            val namee = DataItem(name[i], dob[i])
            newArrayList.add(namee)
        }

        var adapter = MyHolder(newArrayList)


        val swipegesture = object : SwipeGesture(this) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                val from_pos = viewHolder.adapterPosition
                val to_pos = target.adapterPosition
                Collections.swap(newArrayList,from_pos,to_pos)
                adapter.notifyItemMoved(from_pos,to_pos)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when (direction) {

                    ItemTouchHelper.LEFT -> {
                        Toast.makeText(
                            applicationContext,
                            "Item Deleted : ${newArrayList[viewHolder.adapterPosition].name}",
                            Toast.LENGTH_SHORT
                        ).show()
                        adapter.deleteItem(viewHolder.adapterPosition)
                    }

                    ItemTouchHelper.RIGHT -> {
                        val archieveItem = newArrayList[viewHolder.adapterPosition]
                        Toast.makeText(
                            applicationContext,
                            "Item Archieved: ${archieveItem.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                        adapter.deleteItem(viewHolder.adapterPosition)
                        adapter.archived(newArrayList.size, archieveItem)
                    }
                }
            }


        }
        val touchAdapter = ItemTouchHelper(swipegesture)
        touchAdapter.attachToRecyclerView(newRecyclerView)
        newRecyclerView.adapter = adapter


        adapter.setItemClickListener(object : MyHolder.onItemClickListener {

            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, newActivity::class.java)
                intent.putExtra("Name", newArrayList[position].name)
                intent.putExtra("DOB", newArrayList[position].dob)

                startActivity(intent)
            }
        })
    }

}
