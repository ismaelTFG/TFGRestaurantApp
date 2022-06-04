package com.eep.tfgrestaurantapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eep.tfgrestaurantapp.entity.Comandas
import com.eep.tfgrestaurantapp.serviceImpl.ComandaServiceImpl
import java.time.LocalDate
import java.util.ArrayList

class GenerarComandaSinRedActivity : AppCompatActivity() {

    val comandaServiceImpl = ComandaServiceImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generar_comanda_sin_red)

        val mesa = findViewById<EditText>(R.id.mesa)
        val camarero = findViewById<EditText>(R.id.camarero)
        val add = findViewById<Button>(R.id.add)
        val salir = findViewById<Button>(R.id.salirGenerar)

        add.setOnClickListener {

            if (comandaServiceImpl.validacion(mesa.text.toString().toInt(), camarero.text.toString())){

                val comandas = Comandas(mesa.text.toString().toInt(), camarero.text.toString(), LocalDate.now().toString(), ArrayList<String>())

                comandaServiceImpl.add(comandas)
                Toast.makeText(this, "se a añadido la comanda", Toast.LENGTH_SHORT).show()

            }else{

                Toast.makeText(this, "hay un fallo en la comanda al introducir", Toast.LENGTH_SHORT).show()

            }

        }

        salir.setOnClickListener {

            finish()

        }

    }

}