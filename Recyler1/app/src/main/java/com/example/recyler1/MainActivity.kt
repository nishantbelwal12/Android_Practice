package com.example.recyler1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

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
        for(i in name.indices){
            val namee = DataItem(name[i],dob[i])
            newArrayList.add(namee)
        }
        newRecyclerView.adapter = MyAdapter(newArrayList)
    }
}