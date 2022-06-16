package com.eep.tfgrestaurantapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SinRedActivity : AppCompatActivity() {

    /**
     * metodo para cuando inician la vista cree todos los contenidos
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sin_red)
    }

    /**
     * ver comandas
     */
    fun verComanda(view: View){

        val i:Intent = Intent(this, VerComandaSinRedActivity::class.java)

        startActivity(i)

    }

    /**
     * generar la comanda
     */
    fun generarComanda(view: View){

        val i:Intent = Intent(this, GenerarComandaSinRedActivity::class.java)

        startActivity(i)

    }

    /**
     * añadir productos a comanda
     */
    fun addProducto(view: View){

        val i:Intent = Intent(this, AddProductoComandaSinRedActivity::class.java)

        startActivity(i)

    }

    /**
     * borrar producto a comanda
     */
    fun deleteProducto(view: View){

        val i:Intent = Intent(this, DeleteProductoComandaSinRedActivity::class.java)

        startActivity(i)

    }

    /**
     * gestion de los productos guardados
     */
    fun gestionProductos(view: View){

        val i:Intent = Intent(this, GestionProductosActivity::class.java)

        startActivity(i)

    }

}