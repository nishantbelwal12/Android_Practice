package com.example.recycler3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new.*

class newActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val bundle: Bundle?=intent.extras

        var name = bundle?.getString("Name").toString()
        var dob = bundle?.getString("Dob").toString()

        tvName.text = name
        tvDob.text = dob
    }
}