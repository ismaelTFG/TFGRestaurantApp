package com.eep.tfgrestaurantapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.eep.tfgrestaurantapp.serviceImpl.ComandaServiceImpl
import com.eep.tfgrestaurantapp.serviceImpl.ProductoServiceImpl

class AddProductoComandaActivity : AppCompatActivity() {

    //Servicios de comandas y productos
    val comandaServiceImpl = ComandaServiceImpl(this)
    val productoServiceImpl = ProductoServiceImpl(this)

    /**
     * metodo para cuando inician la vista cree todos los contenidos
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_producto_comanda)

        //parametro de los componentes de la vista a modificar
        val text = findViewById<TextView>(R.id.comandaConRed)
        val comanda = findViewById<Spinner>(R.id.Comadaspinner)
        val categoria = findViewById<Spinner>(R.id.Categoriaspinner)
        val producto = findViewById<Spinner>(R.id.Productospinner)
        val buscarCategoria = findViewById<Button>(R.id.buscarcategoria)
        val add = findViewById<Button>(R.id.addproductoComanda)
        val salir = findViewById<Button>(R.id.salirComandaadd)

        //contenido del spinner
        val array = arrayOf("Para empezar", "Ensaladas", "Elaborado a la parrilla", "Bocadillos gourmet", "Para Acompañar", "Caprichos dulces", "Bebidas", "Vinos")

        //Rellenado de los spinner
        comanda.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, comandaServiceImpl.mesas(comandaServiceImpl.listAllRed()))
        categoria.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array)
        producto.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, productoServiceImpl.spinnerProductos2(productoServiceImpl.listAll()))

        /**
         * logica del boton de buscar
         */
        buscarCategoria.setOnClickListener {

            producto.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, productoServiceImpl.spinnerProductos2(productoServiceImpl.findByCategoria(categoria.selectedItem.toString())))

        }

        /**
         * logica del boton de añadir
         */
        add.setOnClickListener {

            val com = comandaServiceImpl.findByMesaRed(comanda.selectedItem.toString())
            val id = producto.selectedItem.toString().split(" ")

            if (com != null) {

                com.productos.add(id.get(0))
                comandaServiceImpl.updateComandaRed(com)

            }

            Toast.makeText(this, "se a añadido el producto", Toast.LENGTH_SHORT).show()

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