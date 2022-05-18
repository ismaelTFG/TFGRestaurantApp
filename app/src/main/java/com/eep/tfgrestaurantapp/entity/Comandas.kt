package com.eep.tfgrestaurantapp.entity

class Comandas(var mesa:Int, var camarero:String, var fecha: String, var productos:ArrayList<String>) {

    fun textProductos(): String{

        var exit = ""

        for (i in productos){

            exit = "$exit$i,"

        }

        return exit

    }

    override fun toString(): String {

        var exit = StringBuilder()

        exit.append("mesa: ").append(mesa)
        exit.append("\ncamarero: ").append(camarero)
        exit.append("\nfecha: ").append(fecha)
        exit.append("\ncodigo del producto: ")

        for (i in productos){

            exit.append(i).append(", ")

        }

        return exit.toString()

    }

}