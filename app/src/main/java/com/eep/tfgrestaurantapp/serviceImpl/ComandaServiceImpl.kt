package com.eep.tfgrestaurantapp.serviceImpl

import android.content.ContentValues
import android.content.Context
import com.eep.tfgrestaurantapp.entity.Comandas
import com.eep.tfgrestaurantapp.entity.Productos
import com.eep.tfgrestaurantapp.repository.FireBase
import com.eep.tfgrestaurantapp.repository.Sqlite
import com.eep.tfgrestaurantapp.service.ComandaService
import java.util.*

class ComandaServiceImpl(context: Context): ComandaService {

    //conexion con sqlite
    val sqlite = Sqlite(context)
    //conexion con firebase
    val fireBase = FireBase().getFireBase()
    //servicio de productos
    val productoServiceImpl = ProductoServiceImpl(context)

    /**
     * metodo para motrar todas las comandas
     * @return lista de las comandas
     */
    override fun listAll(): ArrayList<Comandas> {

        val db = sqlite.writableDatabase
        val fila = db.rawQuery("SELECT * FROM comanda", null)
        val comandas = ArrayList<Comandas>()

        if (fila!!.moveToFirst()){

            while (fila.isAfterLast == false){

                val mesa = fila.getInt(0)
                val camarero = fila.getString(1)
                val fecha = fila.getString(2)

                if (fila.getString(3).equals("sin productos")){

                    comandas.add(Comandas(mesa, camarero, fecha, ArrayList<String>()))

                }else{

                    var productos = fila.getString(3)

                    comandas.add(Comandas(mesa, camarero, fecha, listProductos(productos)))

                }

                fila.moveToNext()

            }

        }

        return comandas

    }

    /**
     *metodo para añadir comanda
     * @param comandas
     */
    override fun add(comandas: Comandas) {

        val db = sqlite.writableDatabase
        val registro = ContentValues()

        registro.put("mesa", comandas.mesa)
        registro.put("camarero", comandas.camarero)
        registro.put("fecha", comandas.fecha)
        registro.put("productos", "sin productos")

        db.insert("comanda", null, registro)

    }

    /**
     * metodo para eliminar comanda por la mesa
     * @param mesa
     */
    override fun delete(mesa: Int) {

        val db = sqlite.writableDatabase

        db.delete("comanda", "mesa=${mesa}", null)
        db.close()

    }

    /**
     * metodo para modificar la comanda
     * @param comandas
     */
    override fun update(comandas: Comandas) {

        val db = sqlite.writableDatabase
        val registro = ContentValues()

        registro.put("camarero", comandas.camarero)
        registro.put("fecha", comandas.fecha)
        registro.put("productos", comandas.textProductos())

        db.update("comanda", registro, "mesa=${comandas.mesa}", null)
        db.close()

    }

    /**
     * metodo para buscar por la mesa
     * @param mesa en formato string
     * @return comanda que puede ser nula
     */
    override fun findByMesa(mesa: String): Comandas? {

        val list = listAll()

        for (i in list){
            if (i.mesa == mesa.toInt()){

                return i

            }
        }

        return null

    }

    /**
     * metodo para mostrar las comandas con un formato
     * @param list de comandas
     * @return texto a mostrar
     */
    override fun viewComanda(list: ArrayList<Comandas>): String {

        var exit = ""

        for (i in list){

            exit = exit+i.toString()+"\n\n"

        }

        return exit

    }

    /**
     * metodo para mostrar las mesas de unas comandas
     * @param list de comandas
     * @return lista de mesas
     */
    override fun mesas(list: ArrayList<Comandas>): ArrayList<Int> {

        val exit = ArrayList<Int>()

        for (i in list){

            exit.add(i.mesa)

        }

        return exit

    }

    /**
     * metodo para validad los parametros para la comanda
     * @param mesa
     * @param camarero
     * @return si son correcto o no
     */
    override fun validacion(mesa: Int, camarero: String): Boolean {

        val list = listAll()

        if (!camarero.equals("")){
            if (list.size > 0){

                for (i in list){
                    if (i.mesa == mesa){

                        return false

                    }
                }

            }

            return true

        }else{

            return false

        }

    }

    /**
     * metodo para mostrarla lista de los productos de una comanda
     * @param string de los productos
     * @return lista de los identificadores de los productos
     */
    override fun listProductos(productos: String): ArrayList<String> {

        val list = ArrayList<String>()
        val text = productos.split(",")

        for (i in text){
            if (!i.equals("")){

                list.add(i)

            }
        }

        return list

    }

    /**
     * metodo para sacar la lista de los productos
     * @param comandas
     * @return lista de los productos
     */
    override fun productos(comandas: Comandas): ArrayList<Productos> {

        val list = productoServiceImpl.listAll()
        val exit = ArrayList<Productos>()

        for (i in list){
            for (j in comandas.productos){
                if (i.id.equals(j)){

                    exit.add(i)

                }
            }
        }

        return exit

    }

    /**
     * metodo para borrar un producto de una comanda
     * @param mesa
     * @param producto
     * @return lista de los productos sin el producto borrado
     */
    override fun deleteProductos(mesa: String, producto: String): ArrayList<String> {

        val productos = findByMesa(mesa)?.productos
        val add = ArrayList<String>()

        if (productos != null) {

            var borrar = true

            for (i in productos){
                if (borrar){
                    if (!i.equals(producto)){

                        add.add(i)

                    }else{

                        borrar = false

                    }
                }else{

                    add.add(i)

                }
            }

        }

        return add

    }

    /**
     * metodo para añadir comanda a la base de datos firebase
     * @param comandas
     */
    override fun addToFirebase(comandas: Comandas) {

        val comandaAdd = hashMapOf(

            "camarero" to comandas.camarero,
            "fecha" to comandas.fecha,
            "productos" to comandas.productos

        )

        fireBase.collection("comandassinpagar").document(comandas.mesa.toString()).set(comandaAdd)

    }

    /**
     * metodo para mostrar las comandas del firebase
     * @return lista de comanda
     */
    override fun listAllRed(): ArrayList<Comandas> {

        val db = sqlite.writableDatabase
        val fila = db.rawQuery("SELECT * FROM comandaCon", null)
        val comandas = ArrayList<Comandas>()

        if (fila!!.moveToFirst()){

            while (fila.isAfterLast == false){

                val mesa = fila.getInt(0)
                val camarero = fila.getString(1)
                val fecha = fila.getString(2)

                if (fila.getString(3).equals("sin productos")){

                    comandas.add(Comandas(mesa, camarero, fecha, ArrayList<String>()))

                }else{

                    val productos = fila.getString(3)

                    comandas.add(Comandas(mesa, camarero, fecha, listProductos(productos)))

                }

                fila.moveToNext()

            }

        }

        return comandas

    }

    /**
     * metodo para eliminar la comanda del firebase
     * @param mesa
     */
    override fun deleteRed(mesa: Int) {

        val db = sqlite.writableDatabase

        db.delete("comandaCon", "mesa=${mesa}", null)
        db.close()

    }

    /**
     * buscar comanda por mesa del firebase
     * @param mesa
     * @return comanda que puede ser nula
     */
    override fun findByMesaRed(mesa: String): Comandas? {

        val list = listAllRed()

        for (i in list){
            if (i.mesa == mesa.toInt()){

                return i

            }
        }

        return null

    }

    /**
     * metodo para validar los parametros de la comanda del firebase
     * @param mesa
     * @param camarero
     * @return si es correcto o no
     */
    override fun validacionRed(mesa: Int, camarero: String): Boolean {

        val list = listAllRed()

        if (!camarero.equals("")){
            if (list.size > 0){

                for (i in list){
                    if (i.mesa == mesa){

                        return false

                    }
                }

            }

            return true

        }else{

            return false

        }

    }

    /**
     * metodo para añadir comanda al firebase
     * @param comandas
     */
    override fun addComandaRed(comandas: Comandas) {

        val db = sqlite.writableDatabase
        val registro = ContentValues()

        registro.put("mesa", comandas.mesa)
        registro.put("camarero", comandas.camarero)
        registro.put("fecha", comandas.fecha)
        registro.put("productos", "sin productos")

        db.insert("comandaCon", null, registro)
        addToFirebase(comandas)

    }

    /**
     * metodo para actualizar las comanda del firebase
     * @param comandas
     */
    override fun updateComandaRed(comandas: Comandas) {

        val db = sqlite.writableDatabase
        val registro = ContentValues()

        registro.put("camarero", comandas.camarero)
        registro.put("fecha", comandas.fecha)
        registro.put("productos", comandas.textProductos())

        db.update("comandaCon", registro, "mesa=${comandas.mesa}", null)
        db.close()
        addToFirebase(comandas)

    }

    /**
     * metodo para borrar los productos de la comandas del firebase
     * @param mesa
     * @param producto
     * @return lista de los identificadores de los productos
     */
    override fun deleteProductosRed(mesa: String, producto: String): ArrayList<String> {

        val productos = findByMesaRed(mesa)?.productos
        val add = ArrayList<String>()

        if (productos != null) {

            var borrar = true

            for (i in productos){
                if (borrar){
                    if (!i.equals(producto)){

                        add.add(i)

                    }else{

                        borrar = false

                    }
                }else{

                    add.add(i)

                }
            }

        }

        return add

    }

}