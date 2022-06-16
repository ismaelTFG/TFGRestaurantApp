package com.eep.tfgrestaurantapp.service

import com.eep.tfgrestaurantapp.entity.Comandas
import com.eep.tfgrestaurantapp.entity.Productos
import java.util.ArrayList

interface ComandaService {

    //metodos del sqlite
    fun listAll(): ArrayList<Comandas>
    fun add(comandas: Comandas)
    fun delete(mesa: Int)
    fun update(comandas: Comandas)
    fun findByMesa(mesa: String): Comandas?

    //metodos adicionales de comandas
    fun viewComanda(list: ArrayList<Comandas>): String
    fun mesas(list: ArrayList<Comandas>): ArrayList<Int>
    fun validacion(mesa: Int, camarero: String): Boolean
    fun listProductos(productos: String): ArrayList<String>
    fun productos(comandas: Comandas): ArrayList<Productos>
    fun deleteProductos(mesa: String, producto: String): ArrayList<String>

    //metodos del firebase
    fun addToFirebase(comandas: Comandas)
    fun listAllRed(): ArrayList<Comandas>
    fun deleteRed(mesa: Int)
    fun findByMesaRed(mesa: String): Comandas?

    //metodos adicionales de comandas con firebase
    fun validacionRed(mesa: Int, camarero: String): Boolean
    fun addComandaRed(comandas: Comandas)
    fun updateComandaRed(comandas: Comandas)
    fun deleteProductosRed(mesa: String, producto: String): ArrayList<String>

}