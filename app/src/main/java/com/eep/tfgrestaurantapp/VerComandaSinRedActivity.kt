package com.eep.tfgrestaurantapp

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.preference.PreferenceManager
import android.view.View
import android.widget.*
import com.eep.tfgrestaurantapp.entity.Comandas
import com.eep.tfgrestaurantapp.service.ComandaService
import com.eep.tfgrestaurantapp.serviceImpl.ComandaServiceImpl
import java.io.File
import java.time.LocalDate
import java.util.ArrayList

class VerComandaSinRedActivity : AppCompatActivity() {

    val comandaServiceImpl = ComandaServiceImpl(this)
    var comanda = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_comanda_sin_red)

        val textView = findViewById<TextView>(R.id.comanda)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val buscarButton = findViewById<Button>(R.id.buscar)
        val salirButton = findViewById<Button>(R.id.salir)
        val pagarButton = findViewById<Button>(R.id.pagar)

        textView.setText(comandaServiceImpl.viewComanda(comandaServiceImpl.listAll()))
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, comandaServiceImpl.mesas(comandaServiceImpl.listAll()))

        buscarButton.setOnClickListener {

            comanda = spinner.selectedItem.toString()
            textView.setText(comandaServiceImpl.findByMesa(comanda).toString())

        }

        salirButton.setOnClickListener {

            finish()

        }

        pagarButton.setOnClickListener {

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

            }).setTitle("Advertencia").setMessage("¿Estas seguro que quieres pagar la comanda?,\nsi lo confirmas no podras recuperar esos datos.").create()

            dialogo.show()

        }

    }

}