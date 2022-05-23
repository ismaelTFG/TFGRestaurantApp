package com.eep.tfgrestaurantapp.service

import com.eep.tfgrestaurantapp.entity.Comandas
import com.eep.tfgrestaurantapp.entity.Productos
import java.util.ArrayList

interface ComandaService {

    fun listAll(): ArrayList<Comandas>
    fun add(comandas: Comandas)
    fun delete(mesa: Int)
    fun update(comandas: Comandas)
    fun findByMesa(mesa: String): Comandas?

    fun viewComanda(list: ArrayList<Comandas>): String
    fun mesas(list: ArrayList<Comandas>): ArrayList<Int>
    fun validacion(mesa: Int, camarero: String): Boolean
    fun listProductos(productos: String): ArrayList<String>
    fun productos(comandas: Comandas): ArrayList<Productos>
    fun deleteProductos(mesa: String, producto: String): ArrayList<String>

}