package com.example.recycler5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new.*

class newActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val bundle:Bundle? = intent.extras

        tvName2.text = bundle?.getString("Name").toString()
        tvDOB2.text = bundle?.getString("DOB").toString()
    }
}