package com.eep.tfgrestaurantapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class InicioActivity : AppCompatActivity() {

    var inicio: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val user = findViewById<TextView>(R.id.usuario)

        inicio = intent.getStringExtra("user").toString()

        user.setText("Bienvenido $inicio")

    }

    fun ver(view: View){

        val i = Intent(this, VerComandaActivity::class.java)

        startActivity(i)

    }

    fun add(view: View){

        val i = Intent(this, AddComandaActivity::class.java)

        i.putExtra("user", inicio)
        startActivity(i)

    }

    fun productos(view: View){

        val i = Intent(this, GestionProductosActivity::class.java)

        startActivity(i)

    }

    fun salir(view: View){

        finish()

    }

}