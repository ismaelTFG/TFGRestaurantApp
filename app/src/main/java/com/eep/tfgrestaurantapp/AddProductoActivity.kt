package com.eep.tfgrestaurantapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.eep.tfgrestaurantapp.entity.Productos
import com.eep.tfgrestaurantapp.serviceImpl.ProductoServiceImpl

class AddProductoActivity : AppCompatActivity() {

    //servicio de los productos
    val productoServiceImpl = ProductoServiceImpl(this)

    /**
     * metodo para cuando inician la vista cree todos los contenidos
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_producto)

        //parametro de los componentes de la vista a modificar
        val productos = findViewById<TextView>(R.id.productos)
        val id = findViewById<EditText>(R.id.identificador)
        val nombre = findViewById<EditText>(R.id.nombre)
        val precio = findViewById<EditText>(R.id.precio)
        val categoria = findViewById<Spinner>(R.id.categoriaAdd)
        val add = findViewById<Button>(R.id.addproducto)
        val salir = findViewById<Button>(R.id.salirAddProducto)

        //array para rellenar el spinner
        val array = arrayOf<String>("Para empezar", "Ensaladas", "Elaborado a la parrilla", "Bocadillos gourmet", "Para Acompañar", "Caprichos dulces", "Bebidas", "Vinos")

        //relleno del spinner
        categoria.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array)

        /**
         * logica del boton de añadir
         */
        add.setOnClickListener {

            val producto = Productos(id.text.toString(), categoria.selectedItem.toString(), nombre.text.toString(), precio.text.toString().toDouble())

            if (productoServiceImpl.validar(producto)){

                productoServiceImpl.add(producto)
                productos.setText(productoServiceImpl.viewProductos(productoServiceImpl.listAll()))
                Toast.makeText(this, "se a añadido el producto", Toast.LENGTH_SHORT).show()

            }else{

                Toast.makeText(this, "hay un fallo en el producto al introducir", Toast.LENGTH_SHORT).show()

            }

        }

        /**
         * logica del boton de salir
         */
        salir.setOnClickListener {

            finish()

        }

    }

}