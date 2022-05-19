package com.eep.tfgrestaurantapp

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.*
import com.eep.tfgrestaurantapp.serviceImpl.ProductoServiceImpl

class DeleteProductoActivity : AppCompatActivity() {

    val productoServiceImpl = ProductoServiceImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_producto)
        val producto = findViewById<TextView>(R.id.productos)
        val categoria = findViewById<Spinner>(R.id.categoriaDelete)
        val productos = findViewById<Spinner>(R.id.spinnerProductos)
        val buscar = findViewById<Button>(R.id.buscarDelete)
        val delete = findViewById<Button>(R.id.delete)
        val salir = findViewById<Button>(R.id.salirDeleteProducto)

        categoria.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, productoServiceImpl.categorias(productoServiceImpl.listAll()))

        buscar.setOnClickListener {

            productos.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, productoServiceImpl.spinnerProductos(productoServiceImpl.findByCategoria(categoria.selectedItem.toString())))

        }

        delete.setOnClickListener {

            val dialog = AlertDialog.Builder(this).setPositiveButton("si", DialogInterface.OnClickListener { dialogInterface, i ->

                val borrar = productos.selectedItem.toString()

                productoServiceImpl.delete(borrar)
                producto.setText(productoServiceImpl.viewProductos(productoServiceImpl.listAll()))

                Toast.makeText(this, "producto eliminado", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()

            }).setNegativeButton("no", DialogInterface.OnClickListener { dialogInterface, i ->

                Toast.makeText(this, "operecion cancelada", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()

            }).setTitle("Advertencia").setMessage("¿Estas seguro que quieres eliminar este producto?,\nsi lo confirmas no podras recuperar esos datos.").create()

            dialog.show()

        }

        salir.setOnClickListener {

            finish()

        }

    }

}