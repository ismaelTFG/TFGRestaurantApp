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

class AddProductoComandaSinRedActivity : AppCompatActivity() {

    val comandaServiceImpl = ComandaServiceImpl(this)
    val productoServiceImpl = ProductoServiceImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_producto_comanda_sin_red)

        val text = findViewById<TextView>(R.id.comandasAdd)
        val comanda = findViewById<Spinner>(R.id.comandaSpinner)
        val categoria = findViewById<Spinner>(R.id.categoriaSpinner)
        val producto = findViewById<Spinner>(R.id.productosSpinner)
        val buscarComanda = findViewById<Button>(R.id.buscarAddComada)
        val buscarCategoria = findViewById<Button>(R.id.buscarCategoriaAdd)
        val add = findViewById<Button>(R.id.Add)
        val salir = findViewById<Button>(R.id.salirAdd)

        val array = arrayOf<String>("Para empezar", "Ensaladas", "Elaborado a la parrilla", "Bocadillos gourmet", "Para Acompañar", "Caprichos dulces", "Bebidas", "Vinos")

        text.setText(comandaServiceImpl.viewComanda(comandaServiceImpl.listAll()))
        comanda.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, comandaServiceImpl.mesas(comandaServiceImpl.listAll()))
        categoria.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array)
        producto.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, productoServiceImpl.spinnerProductos2(productoServiceImpl.listAll()))

        buscarComanda.setOnClickListener {

            text.setText(comandaServiceImpl.findByMesa(comanda.selectedItem.toString()).toString())

        }

        buscarCategoria.setOnClickListener {

            producto.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, productoServiceImpl.spinnerProductos2(productoServiceImpl.findByCategoria(categoria.selectedItem.toString())))

        }

        add.setOnClickListener {

            val com = comandaServiceImpl.findByMesa(comanda.selectedItem.toString())
            val id = producto.selectedItem.toString().split(" ")

            if (com != null) {

                com.productos.add(id.get(0))
                comandaServiceImpl.update(com)

            }

            text.setText(comandaServiceImpl.findByMesa(comanda.selectedItem.toString()).toString())

        }

        salir.setOnClickListener {

            finish()

        }

    }

}