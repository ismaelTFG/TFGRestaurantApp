package com.eep.tfgrestaurantapp.entity

import java.lang.StringBuilder

class Productos(var id:String, var categoria:String, var nombre:String, var precio:Double){

    /**
     * metodo toString personalizado
     */
    override fun toString(): String {

        val exit = StringBuilder()

        exit.append("identificador: ").append(id)
        exit.append(", categoria: ").append(categoria)
        exit.append(", nombre: ").append(nombre)
        exit.append(", precio: ").append(precio)

        return exit.toString()

    }

}