package com.eep.tfgrestaurantapp.entity

class Comandas(var mesa:Int, var camarero:String, var fecha: String, var productos:ArrayList<String>) {

    /**
     * metodo para motrar los productos el los activitys
     * @return texto modificado
     */
    fun textProductos(): String{

        var exit = ""

        for (i in productos){

            exit = "$exit$i,"

        }

        return exit

    }

    /**
     * metodo toString personalizado
     */
    override fun toString(): String {

        val exit = StringBuilder()

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