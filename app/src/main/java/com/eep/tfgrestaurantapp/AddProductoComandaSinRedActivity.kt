package com.eep.tfgrestaurantapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.eep.tfgrestaurantapp.serviceImpl.ComandaServiceImpl
import com.eep.tfgrestaurantapp.serviceImpl.ProductoServiceImpl

class AddProductoComandaSinRedActivity : AppCompatActivity() {

    //Servicios de comandas y productos
    val comandaServiceImpl = ComandaServiceImpl(this)
    val productoServiceImpl = ProductoServiceImpl(this)

    /**
     * metodo para cuando inician la vista cree todos los contenidos
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_producto_comanda_sin_red)

        //parametro de los componentes de la vista a modificar
        val text = findViewById<TextView>(R.id.comandasAdd)
        val comanda = findViewById<Spinner>(R.id.comandaSpinner)
        val categoria = findViewById<Spinner>(R.id.categoriaSpinner)
        val producto = findViewById<Spinner>(R.id.productosSpinner)
        val buscarComanda = findViewById<Button>(R.id.buscarAddComada)
        val buscarCategoria = findViewById<Button>(R.id.buscarCategoriaAdd)
        val add = findViewById<Button>(R.id.Add)
        val salir = findViewById<Button>(R.id.salirAdd)

        //contenido del spinner
        val array = arrayOf<String>("Para empezar", "Ensaladas", "Elaborado a la parrilla", "Bocadillos gourmet", "Para Acompañar", "Caprichos dulces", "Bebidas", "Vinos")

        //Rellenado del contenido a mostrar
        text.setText(comandaServiceImpl.viewComanda(comandaServiceImpl.listAll()))
        comanda.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, comandaServiceImpl.mesas(comandaServiceImpl.listAll()))
        categoria.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array)
        producto.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, productoServiceImpl.spinnerProductos2(productoServiceImpl.listAll()))

        /**
         * logica del boton de buscar comanda
         */
        buscarComanda.setOnClickListener {

            text.setText(comandaServiceImpl.findByMesa(comanda.selectedItem.toString()).toString())

        }

        /**
         * logica del boton de buscar categoria
         */
        buscarCategoria.setOnClickListener {

            producto.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, productoServiceImpl.spinnerProductos2(productoServiceImpl.findByCategoria(categoria.selectedItem.toString())))

        }

        /**
         * logica del boton de añadir
         */
        add.setOnClickListener {

            val com = comandaServiceImpl.findByMesa(comanda.selectedItem.toString())
            val id = producto.selectedItem.toString().split(" ")

            if (com != null) {

                com.productos.add(id.get(0))
                comandaServiceImpl.update(com)

            }

            text.setText(comandaServiceImpl.findByMesa(comanda.selectedItem.toString()).toString())

        }

        /**
         * logica del boton de salir
         */
        salir.setOnClickListener {

            finish()

        }

    }

}