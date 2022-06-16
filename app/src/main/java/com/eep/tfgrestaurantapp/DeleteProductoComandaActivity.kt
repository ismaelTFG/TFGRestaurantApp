package com.eep.tfgrestaurantapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.eep.tfgrestaurantapp.serviceImpl.ComandaServiceImpl
import com.eep.tfgrestaurantapp.serviceImpl.ProductoServiceImpl

class DeleteProductoComandaActivity : AppCompatActivity() {

    //Servicios de productos y comanda
    val comandaServiceImpl = ComandaServiceImpl(this)
    val productoServiceImpl = ProductoServiceImpl(this)

    /**
     * metodo para cuando inician la vista cree todos los contenidos
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_producto_comanda)

        //parametro de los componentes de la vista a modificar
        val text = findViewById<TextView>(R.id.comandaConRed)
        val comandas = findViewById<Spinner>(R.id.spinnerComanda)
        val producto = findViewById<Spinner>(R.id.spinnerProductosComanda)
        val buscar = findViewById<Button>(R.id.buscarDeleteProductoComanda)
        val delete = findViewById<Button>(R.id.deleteProductoComandaCon)
        val salir = findViewById<Button>(R.id.exit)

        //Relleno del spinner de comandas
        comandas.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, comandaServiceImpl.mesas(comandaServiceImpl.listAllRed()))

        /**
         * logica del boton de buscar
         */
        buscar.setOnClickListener {

            producto.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, productoServiceImpl.spinnerProductos2(comandaServiceImpl.productos(
                comandaServiceImpl.findByMesaRed(comandas.selectedItem.toString())!!
            )))

        }

        /**
         * logica del boton de borrado
         */
        delete.setOnClickListener {

            val com = comandaServiceImpl.findByMesaRed(comandas.selectedItem.toString())
            val id = producto.selectedItem.toString().split(" ")

            if (com != null) {

                com.productos = comandaServiceImpl.deleteProductosRed("${com.mesa}", id.get(0))
                comandaServiceImpl.updateComandaRed(com)

            }

            Toast.makeText(this, "se a eliminado el producto", Toast.LENGTH_SHORT).show()

        }

        /**
         * logica del boton de salir
         */
        salir.setOnClickListener {

            text.setText(comandaServiceImpl.viewComanda(comandaServiceImpl.listAllRed()))
            finish()

        }

    }

}