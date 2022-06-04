package com.eep.tfgrestaurantapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.eep.tfgrestaurantapp.serviceImpl.ComandaServiceImpl

class VerComandaActivity : AppCompatActivity() {

    val comandaServiceImpl = ComandaServiceImpl(this)
    var comanda = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_comanda)

        val textView = findViewById<TextView>(R.id.comandaConRed)
        val spinner = findViewById<Spinner>(R.id.spinner2)
        val buscar = findViewById<Button>(R.id.buscarConRed)
        val salir = findViewById<Button>(R.id.salirConRed)
        val pagar = findViewById<Button>(R.id.pagada)
        val add = findViewById<Button>(R.id.addProductos)
        val delete = findViewById<Button>(R.id.deleteProductos)

        textView.setText(comandaServiceImpl.viewComanda(comandaServiceImpl.listAllRed()))
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, comandaServiceImpl.mesas(comandaServiceImpl.listAllRed()))

        buscar.setOnClickListener {

            comanda = spinner.selectedItem.toString()
            textView.setText(comandaServiceImpl.findByMesaRed(comanda).toString())

        }

        salir.setOnClickListener {

            finish()

        }

        pagar.setOnClickListener {

            val dialogo = AlertDialog.Builder(this).setPositiveButton("si", DialogInterface.OnClickListener { dialogInterface, i ->

                comanda = spinner.selectedItem.toString()
                comandaServiceImpl.deleteRed(comanda.toInt())
                textView.setText(comandaServiceImpl.viewComanda(comandaServiceImpl.listAllRed()))
                spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, comandaServiceImpl.mesas(comandaServiceImpl.listAllRed()))

                Toast.makeText(this, "comanda pagada", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()

            }).setNegativeButton("no", DialogInterface.OnClickListener { dialogInterface, i ->

                Toast.makeText(this, "operecion cancelada", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()

            }).setTitle("Advertencia").setMessage("¿Estas seguro que quieres pagar la comanda?,\nsi lo confirmas no podras recuperar esos datos.").create()

            dialogo.show()

        }

        add.setOnClickListener {

            val i = Intent(this, AddProductoComandaActivity::class.java)

            startActivity(i)

        }

        delete.setOnClickListener {

            val i = Intent(this, DeleteProductoComandaActivity::class.java)

            startActivity(i)

        }

    }
}