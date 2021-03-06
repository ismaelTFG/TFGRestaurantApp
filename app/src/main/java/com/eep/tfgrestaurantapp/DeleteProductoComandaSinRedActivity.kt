package com.eep.tfgrestaurantapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.eep.tfgrestaurantapp.serviceImpl.ComandaServiceImpl
import com.eep.tfgrestaurantapp.serviceImpl.ProductoServiceImpl

class DeleteProductoComandaSinRedActivity : AppCompatActivity() {

    //Servicios de productos y comanda
    val comandaServiceImpl = ComandaServiceImpl(this)
    val productoServiceImpl = ProductoServiceImpl(this)

    /**
     * metodo para cuando inician la vista cree todos los contenidos
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_producto_comanda_sin_red)

        //parametro de los componentes de la vista a modificar
        val text = findViewById<TextView>(R.id.comandaDelete)
        val comandas = findViewById<Spinner>(R.id.spinnerBuscar)
        val producto = findViewById<Spinner>(R.id.spinnerProducto)
        val buscar = findViewById<Button>(R.id.buscarDeleteProducto)
        val delete = findViewById<Button>(R.id.deleteProductoComanda)
        val salir = findViewById<Button>(R.id.salirDelete)

        //Relleno del contenido a mostrar
        text.setText(comandaServiceImpl.viewComanda(comandaServiceImpl.listAll()))
        comandas.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, comandaServiceImpl.mesas(comandaServiceImpl.listAll()))

        /**
         * logica del boton de buscar
         */
        buscar.setOnClickListener {

            text.setText(comandaServiceImpl.findByMesa(comandas.selectedItem.toString()).toString())

            producto.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, productoServiceImpl.spinnerProductos2(comandaServiceImpl.productos(
                comandaServiceImpl.findByMesa(comandas.selectedItem.toString())!!
            )))

        }

        /**
         * logica del boton de borrado
         */
        delete.setOnClickListener {

            val com = comandaServiceImpl.findByMesa(comandas.selectedItem.toString())
            val id = producto.selectedItem.toString().split(" ")

            if (com != null) {

                com.productos = comandaServiceImpl.deleteProductos("${com.mesa}", id.get(0))
                comandaServiceImpl.update(com)

            }

            text.setText(comandaServiceImpl.findByMesa(comandas.selectedItem.toString()).toString())

        }

        /**
         * logica del boton de salir
         */
        salir.setOnClickListener {

            finish()

        }

    }

}