package com.eep.tfgrestaurantapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.eep.tfgrestaurantapp.serviceImpl.ProductoServiceImpl

class GestionProductosActivity : AppCompatActivity() {

    val productoServiceImpl = ProductoServiceImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_productos)

        val productos = findViewById<TextView>(R.id.productos)
        val categoria = findViewById<Spinner>(R.id.categoria)
        val buscar = findViewById<Button>(R.id.buscarCategoria)
        val add = findViewById<Button>(R.id.addProducto)
        val delete = findViewById<Button>(R.id.deleteProducto)
        val salir = findViewById<Button>(R.id.salirProducto)

        val array = arrayOf<String>("Para empezar", "Ensaladas", "Elaborado a la parrilla", "Bocadillos gourmet", "Para Acompañar", "Caprichos dulces", "Bebidas", "Vinos")

        productos.setText(productoServiceImpl.viewProductos(productoServiceImpl.listAll()))
        categoria.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array)

        buscar.setOnClickListener {

            val categori = categoria.selectedItem.toString()

            productos.setText(productoServiceImpl.viewProductos(productoServiceImpl.findByCategoria(categori)))

        }

        add.setOnClickListener {

            val i = Intent(this, AddProductoActivity::class.java)

            startActivity(i)

        }

        delete.setOnClickListener {

            val i = Intent(this, DeleteProductoActivity::class.java)

            startActivity(i)

        }

        salir.setOnClickListener {

            finish()

        }

    }

}