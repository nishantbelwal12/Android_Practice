package com.example.a3_2_edureka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), Communicator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentLeft = LeftFragment()
        val fragmentRight = RightFragment()

        supportFragmentManager.beginTransaction().replace(
            R.id.flLeft,fragmentLeft).commit()

        supportFragmentManager.beginTransaction().replace(
            R.id.flRight,fragmentRight).commit()
    }

    override fun passDataCom(color: Int) {

        val bundle = Bundle()
        bundle.putInt("color",color)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentB = RightFragment()
        fragmentB.arguments = bundle
        transaction.replace(R.id.flRight,fragmentB)
        transaction.commit()
    }

}