package com.eep.tfgrestaurantapp.service

import com.eep.tfgrestaurantapp.entity.Productos
import java.util.ArrayList

interface ProductoService {

    //metodos para productos
    fun listAll(): ArrayList<Productos>
    fun add(productos: Productos)
    fun delete(id: String)
    fun findByCategoria(categoria: String): ArrayList<Productos>

    //metodos adicionales de los productos
    fun viewProductos(list: ArrayList<Productos>): String
    fun validar(productos: Productos): Boolean
    fun categorias(list: ArrayList<Productos>): ArrayList<String>
    fun spinnerProductos(list: ArrayList<Productos>): ArrayList<String>
    fun spinnerProductos2(list: ArrayList<Productos>): ArrayList<String>

}