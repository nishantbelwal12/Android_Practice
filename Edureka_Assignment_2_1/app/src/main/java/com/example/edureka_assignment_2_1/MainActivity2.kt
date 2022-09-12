package com.example.edureka_assignment_2_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

       // val bundle: Bundle? = intent.extras
        val urlLoad = intent.getStringExtra("EXTRA_Url")
        wvSample.webViewClient = WebViewClient()
        wvSample.loadUrl(urlLoad.toString())

    }
}