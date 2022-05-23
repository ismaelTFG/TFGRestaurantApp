package com.eep.tfgrestaurantapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.eep.tfgrestaurantapp.R
import com.eep.tfgrestaurantapp.serviceImpl.ComandaServiceImpl
import com.eep.tfgrestaurantapp.serviceImpl.ProductoServiceImpl

class DeleteProductoComandaSinRedActivity : AppCompatActivity() {

    val comandaServiceImpl = ComandaServiceImpl(this)
    val productoServiceImpl = ProductoServiceImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_producto_comanda_sin_red)

        val text = findViewById<TextView>(R.id.comandaDelete)
        val comandas = findViewById<Spinner>(R.id.spinnerBuscar)
        val producto = findViewById<Spinner>(R.id.spinnerProducto)
        val buscar = findViewById<Button>(R.id.buscarDeleteProducto)
        val delete = findViewById<Button>(R.id.deleteProductoComanda)
        val salir = findViewById<Button>(R.id.salirDelete)

        text.setText(comandaServiceImpl.viewComanda(comandaServiceImpl.listAll()))
        comandas.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, comandaServiceImpl.mesas(comandaServiceImpl.listAll()))

        buscar.setOnClickListener {

            text.setText(comandaServiceImpl.findByMesa(comandas.selectedItem.toString()).toString())

            producto.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, productoServiceImpl.spinnerProductos2(comandaServiceImpl.productos(
                comandaServiceImpl.findByMesa(comandas.selectedItem.toString())!!
            )))

        }

        delete.setOnClickListener {

            val com = comandaServiceImpl.findByMesa(comandas.selectedItem.toString())
            val id = producto.selectedItem.toString().split(" ")

            if (com != null) {

                com.productos = comandaServiceImpl.deleteProductos("${com.mesa}", id.get(0))
                comandaServiceImpl.update(com)

            }

            text.setText(comandaServiceImpl.findByMesa(comandas.selectedItem.toString()).toString())

        }

        salir.setOnClickListener {

            finish()

        }

    }

}