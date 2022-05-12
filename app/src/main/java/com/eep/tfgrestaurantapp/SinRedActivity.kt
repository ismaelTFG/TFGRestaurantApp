package com.eep.tfgrestaurantapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SinRedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sin_red)
    }

    fun verComanda(view: View){

        val i:Intent = Intent(this, VerComandaSinRedActivity::class.java)

        startActivity(i)

    }

    fun generarComanda(view: View){

        val i:Intent = Intent(this, GenerarComandaSinRedActivity::class.java)

        startActivity(i)

    }

    fun addProducto(view: View){

        val i:Intent = Intent(this, AddProductoComandaSinRedActivity::class.java)

        startActivity(i)

    }

    fun deleteProducto(view: View){

        val i:Intent = Intent(this, DeleteProductoComandaSinRedActivity::class.java)

        startActivity(i)

    }

    fun gestionProductos(view: View){

        val i:Intent = Intent(this, GestionProductosActivity::class.java)

        startActivity(i)

    }

}