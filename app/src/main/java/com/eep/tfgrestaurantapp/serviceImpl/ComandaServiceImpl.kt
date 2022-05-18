package com.eep.tfgrestaurantapp.serviceImpl


import android.content.ContentValues
import android.content.Context
import com.eep.tfgrestaurantapp.entity.Comandas
import com.eep.tfgrestaurantapp.repository.Sqlite
import com.eep.tfgrestaurantapp.service.ComandaService
import java.util.ArrayList

class ComandaServiceImpl(context: Context): ComandaService {

    val sqlite = Sqlite(context)

    override fun listAll(): ArrayList<Comandas> {

        val db = sqlite.writableDatabase
        val fila = db.rawQuery("SELECT * FROM comanda", null)
        val comandas = ArrayList<Comandas>()

        if (fila!!.moveToFirst()){

            while (fila.isAfterLast == false){

                var mesa = fila.getInt(0)
                var camarero = fila.getString(1)
                var fecha = fila.getString(2)
                var productos = fila.getString(3)

                comandas.add(Comandas(mesa, camarero, fecha, listProductos(productos)))

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
        registro.put("productos", comandas.textProductos())

        db.insert("comanda", null, registro)

    }

    override fun delete(mesa: Int) {

        val db = sqlite.writableDatabase
        val cant = db.delete("comanda", "mesa=${mesa}", null)
        db.close()

    }

    override fun update(comandas: Comandas) {
        TODO("Not yet implemented")
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

    override fun listProductos(productos: String): ArrayList<String> {

        var list = ArrayList<String>()
        var text = productos.split(",")

        for (i in text){

            list.add(i)

        }

        return list

    }

}