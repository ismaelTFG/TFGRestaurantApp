package com.eep.tfgrestaurantapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class InicioActivity : AppCompatActivity() {

    //usuario iniciado
    var inicio: String = ""

    /**
     * metodo para cuando inician la vista cree todos los contenidos
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        //parametro de los componentes de la vista a modificar
        val user = findViewById<TextView>(R.id.usuario)

        //usuario iniciado
        inicio = intent.getStringExtra("user").toString()

        //relleno del contenido
        user.setText("Bienvenido $inicio")

    }

    /**
     * ver comandas
     */
    fun ver(view: View){

        val i = Intent(this, VerComandaActivity::class.java)

        startActivity(i)

    }

    /**
     * añadir comandas
     */
    fun add(view: View){

        val i = Intent(this, AddComandaActivity::class.java)

        i.putExtra("user", inicio)
        startActivity(i)

    }

    /**
     * ver productos
     */
    fun productos(view: View){

        val i = Intent(this, GestionProductosActivity::class.java)

        startActivity(i)

    }

    /**
     * salir
     */
    fun salir(view: View){

        finish()

    }

}