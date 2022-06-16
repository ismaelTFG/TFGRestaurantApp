package com.eep.tfgrestaurantapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.eep.tfgrestaurantapp.serviceImpl.ProductoServiceImpl

class GestionProductosActivity : AppCompatActivity() {

    //Servicos de productos
    val productoServiceImpl = ProductoServiceImpl(this)

    /**
     * metodo para cuando inician la vista cree todos los contenidos
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_productos)

        //parametro de los componentes de la vista a modificar
        val productos = findViewById<TextView>(R.id.productos)
        val categoria = findViewById<Spinner>(R.id.categoria)
        val buscar = findViewById<Button>(R.id.buscarCategoria)
        val add = findViewById<Button>(R.id.addProducto)
        val delete = findViewById<Button>(R.id.deleteProducto)
        val salir = findViewById<Button>(R.id.salirProducto)

        //contenido del spinner
        val array = arrayOf("Para empezar", "Ensaladas", "Elaborado a la parrilla", "Bocadillos gourmet", "Para Acompañar", "Caprichos dulces", "Bebidas", "Vinos")

        //Relleno de contenido de la vista
        productos.setText(productoServiceImpl.viewProductos(productoServiceImpl.listAll()))
        categoria.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array)

        /**
         * logica del boton de buscar
         */
        buscar.setOnClickListener {

            val categori = categoria.selectedItem.toString()

            productos.setText(productoServiceImpl.viewProductos(productoServiceImpl.findByCategoria(categori)))

        }

        /**
         * logica del boton de añadir
         */
        add.setOnClickListener {

            val i = Intent(this, AddProductoActivity::class.java)

            startActivity(i)

        }

        /**
         * logica del boton de borrar
         */
        delete.setOnClickListener {

            val i = Intent(this, DeleteProductoActivity::class.java)

            startActivity(i)

        }

        /**
         * logica del boton de salir
         */
        salir.setOnClickListener {

            finish()

        }

    }

}