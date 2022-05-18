package com.eep.tfgrestaurantapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sinRed(view: View){

        val i = Intent(this, SinRedActivity::class.java)

        startActivity(i)

    }

    fun conRed(view: View){

        val i = Intent(this, ConRedActivity::class.java)

        startActivity(i)

    }

}