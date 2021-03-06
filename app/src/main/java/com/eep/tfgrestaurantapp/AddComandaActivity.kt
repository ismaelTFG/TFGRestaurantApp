package com.eep.tfgrestaurantapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.eep.tfgrestaurantapp.entity.Comandas
import com.eep.tfgrestaurantapp.serviceImpl.ComandaServiceImpl
import java.time.LocalDate
import java.util.ArrayList

class AddComandaActivity : AppCompatActivity() {

    //servicio de las comandas
    val comandaServiceImpl = ComandaServiceImpl(this)

    /**
     * metodo para cuando inician la vista cree todos los contenidos
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_comanda)

        //parametro pasado de la vista anterior
        val camarero = intent.getStringExtra("user").toString()
        //parametro de los componentes de la vista a modificar
        val mesa = findViewById<EditText>(R.id.mesaComanda)
        val add = findViewById<Button>(R.id.addComanda)
        val salir = findViewById<Button>(R.id.salirAddComanda)

        /**
         * logica del boton de la añadir
         */
        add.setOnClickListener {

            if (comandaServiceImpl.validacionRed(mesa.text.toString().toInt(), camarero)){

                val comandas = Comandas(mesa.text.toString().toInt(), camarero, LocalDate.now().toString(), ArrayList<String>())

                comandaServiceImpl.addComandaRed(comandas)
                Toast.makeText(this, "se a añadido la comanda", Toast.LENGTH_SHORT).show()

            }else{

                Toast.makeText(this, "hay un fallo en la comanda al introducir", Toast.LENGTH_SHORT).show()

            }

        }

        /**
         * logica del boton para salir
         */
        salir.setOnClickListener {

            finish()

        }

    }

}