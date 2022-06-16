package com.eep.tfgrestaurantapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ConRedActivity : AppCompatActivity() {

    /**
     * metodo para cuando inician la vista cree todos los contenidos
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_con_red)

        //parametro de los componentes de la vista a modificar
        val user = findViewById<EditText>(R.id.nombreUsuario)
        val inicio = findViewById<Button>(R.id.iniciar)

        /**
         * logica del boton de iniciar
         */
        inicio.setOnClickListener {

            val i = Intent(this, InicioActivity::class.java)

            i.putExtra("user", user.text.toString())
            startActivity(i)

        }

    }

}