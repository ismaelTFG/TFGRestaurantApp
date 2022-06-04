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

    val sqlite = Sqlite(context)
    val fireBase = FireBase().getFireBase()
    val productoServiceImpl = ProductoServiceImpl(context)

    override fun listAll(): ArrayList<Comandas> {

        val db = sqlite.writableDatabase
        val fila = db.rawQuery("SELECT * FROM comanda", null)
        val comandas = ArrayList<Comandas>()

        if (fila!!.moveToFirst()){

            while (fila.isAfterLast == false){

                var mesa = fila.getInt(0)
                var camarero = fila.getString(1)
                var fecha = fila.getString(2)

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

    override fun add(comandas: Comandas) {

        val db = sqlite.writableDatabase
        val registro = ContentValues()

        registro.put("mesa", comandas.mesa)
        registro.put("camarero", comandas.camarero)
        registro.put("fecha", comandas.fecha)
        registro.put("productos", "sin productos")

        db.insert("comanda", null, registro)

    }

    override fun delete(mesa: Int) {

        val db = sqlite.writableDatabase

        db.delete("comanda", "mesa=${mesa}", null)
        db.close()

    }

    override fun update(comandas: Comandas) {

        val db = sqlite.writableDatabase
        val registro = ContentValues()

        registro.put("camarero", comandas.camarero)
        registro.put("fecha", comandas.fecha)
        registro.put("productos", comandas.textProductos())

        db.update("comanda", registro, "mesa=${comandas.mesa}", null)
        db.close()

    }

    override fun findByMesa(mesa: String): Comandas? {

        val list = listAll()

        for (i in list){
            if (i.mesa == mesa.toInt()){

                return i

            }
        }

        return null

    }

    override fun viewComanda(list: ArrayList<Comandas>): String {

        var exit = ""

        for (i in list){

            exit = exit+i.toString()+"\n\n"

        }

        return exit

    }

    override fun mesas(list: ArrayList<Comandas>): ArrayList<Int> {

        var exit = ArrayList<Int>()

        for (i in list){

            exit.add(i.mesa)

        }

        return exit

    }

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

    override fun listProductos(productos: String): ArrayList<String> {

        var list = ArrayList<String>()
        var text = productos.split(",")

        for (i in text){
            if (!i.equals("")){

                list.add(i)

            }
        }

        return list

    }

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

    override fun addToFirebase(comandas: Comandas) {

        val comandaAdd = hashMapOf(

            "camarero" to comandas.camarero,
            "fecha" to comandas.fecha,
            "productos" to comandas.productos

        )

        fireBase.collection("comandassinpagar").document(comandas.mesa.toString()).set(comandaAdd)

    }

    override fun listAllRed(): ArrayList<Comandas> {

        val db = sqlite.writableDatabase
        val fila = db.rawQuery("SELECT * FROM comandaCon", null)
        val comandas = ArrayList<Comandas>()

        if (fila!!.moveToFirst()){

            while (fila.isAfterLast == false){

                var mesa = fila.getInt(0)
                var camarero = fila.getString(1)
                var fecha = fila.getString(2)

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

    override fun deleteRed(mesa: Int) {

        val db = sqlite.writableDatabase

        db.delete("comanda", "mesa=${mesa}", null)
        db.close()

    }

    override fun findByMesaRed(mesa: String): Comandas? {

        val list = listAllRed()

        for (i in list){
            if (i.mesa == mesa.toInt()){

                return i

            }
        }

        return null

    }

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

}