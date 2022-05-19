package com.eep.tfgrestaurantapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.eep.tfgrestaurantapp.entity.Productos
import com.eep.tfgrestaurantapp.serviceImpl.ProductoServiceImpl

class AddProductoActivity : AppCompatActivity() {

    val productoServiceImpl = ProductoServiceImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_producto)

        val productos = findViewById<TextView>(R.id.productos)
        val id = findViewById<EditText>(R.id.identificador)
        val nombre = findViewById<EditText>(R.id.nombre)
        val precio = findViewById<EditText>(R.id.precio)
        val categoria = findViewById<Spinner>(R.id.categoriaAdd)
        val add = findViewById<Button>(R.id.addproducto)
        val salir = findViewById<Button>(R.id.salirAddProducto)

        val array = arrayOf<String>("Para empezar", "Ensaladas", "Elaborado a la parrilla", "Bocadillos gourmet", "Para Acompañar", "Caprichos dulces", "Bebidas", "Vinos")

        categoria.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array)

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

        salir.setOnClickListener {

            finish()

        }

    }

}