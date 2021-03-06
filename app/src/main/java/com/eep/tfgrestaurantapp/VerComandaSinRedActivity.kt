package com.eep.tfgrestaurantapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.eep.tfgrestaurantapp.serviceImpl.ComandaServiceImpl

class VerComandaSinRedActivity : AppCompatActivity() {

    //Servicios de comandas
    val comandaServiceImpl = ComandaServiceImpl(this)
    var comanda = ""

    /**
     * metodo para cuando inician la vista cree todos los contenidos
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_comanda_sin_red)

        //parametro de los componentes de la vista a modificar
        val textView = findViewById<TextView>(R.id.comanda)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val buscar = findViewById<Button>(R.id.buscar)
        val salir = findViewById<Button>(R.id.salir)
        val pagar = findViewById<Button>(R.id.pagar)

        //Relleno del contenido de la vista
        textView.setText(comandaServiceImpl.viewComanda(comandaServiceImpl.listAll()))
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, comandaServiceImpl.mesas(comandaServiceImpl.listAll()))

        /**
         * logica del boton buscar
         */
        buscar.setOnClickListener {

            comanda = spinner.selectedItem.toString()
            textView.setText(comandaServiceImpl.findByMesa(comanda).toString())

        }

        /**
         * logica del boton salir
         */
        salir.setOnClickListener {

            finish()

        }

        /**
         * logica del boton pagar
         */
        pagar.setOnClickListener {

            val dialogo = AlertDialog.Builder(this).setPositiveButton("si", DialogInterface.OnClickListener { dialogInterface, i ->

                comanda = spinner.selectedItem.toString()
                comandaServiceImpl.delete(comanda.toInt())
                textView.setText(comandaServiceImpl.viewComanda(comandaServiceImpl.listAll()))
                spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, comandaServiceImpl.mesas(comandaServiceImpl.listAll()))

                Toast.makeText(this, "comanda pagada", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()

            }).setNegativeButton("no", DialogInterface.OnClickListener { dialogInterface, i ->

                Toast.makeText(this, "operecion cancelada", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()

            }).setTitle("Advertencia").setMessage("??Estas seguro que quieres pagar la comanda?,\nsi lo confirmas no podras recuperar esos datos.").create()

            dialogo.show()

        }

    }

}