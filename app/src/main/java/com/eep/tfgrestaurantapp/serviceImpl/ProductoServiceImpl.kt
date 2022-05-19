package com.eep.tfgrestaurantapp.serviceImpl

import android.content.ContentValues
import android.content.Context
import com.eep.tfgrestaurantapp.entity.Productos
import com.eep.tfgrestaurantapp.repository.Sqlite
import com.eep.tfgrestaurantapp.service.ProductoService
import java.util.ArrayList

class ProductoServiceImpl(context: Context): ProductoService {

    val sqlite = Sqlite(context)

    override fun listAll(): ArrayList<Productos> {

        val db = sqlite.writableDatabase
        val fila = db.rawQuery("SELECT * FROM producto", null)
        val productos = ArrayList<Productos>()

        if (fila!!.moveToFirst()){

            while (fila.isAfterLast == false){

                var id = fila.getString(0)
                var categoria = fila.getString(1)
                var nombre = fila.getString(2)
                var precio = fila.getDouble(3)

                productos.add(Productos(id, categoria, nombre, precio))

                fila.moveToNext()

            }

        }

        return productos

    }

    override fun add(productos: Productos) {

        val db = sqlite.writableDatabase
        val registro = ContentValues()

        registro.put("id", productos.id)
        registro.put("categoria", productos.categoria)
        registro.put("nombre", productos.nombre)
        registro.put("precio", productos.precio)

        db.insert("producto", null, registro)

    }

    override fun delete(id: String) {

        val db = sqlite.writableDatabase

        db.delete("producto", "id='${id}'", null)
        db.close()

    }

    override fun findByCategoria(categoria: String): ArrayList<Productos> {

        val list = listAll()
        val exit = ArrayList<Productos>()

        for (i in list){
            if (i.categoria.equals(categoria)){

                exit.add(i)

            }
        }

        return exit

    }

    override fun viewProductos(list: ArrayList<Productos>): String {

        var exit = ""

        for (i in list){

            exit = exit+i.toString()+"\n\n"

        }

        return exit

    }

    override fun validar(productos: Productos): Boolean {

        val list = listAll()

        if(!productos.nombre.equals("")){
            if (productos.precio > 0){
                if (list.size > 0){

                    for (i in list){
                        if (i.id.equals(productos.id)){

                            return false

                        }
                    }

                }

                return true

            }
        }

        return false

    }

    override fun categorias(list: ArrayList<Productos>): ArrayList<String> {

        val exit = ArrayList<String>()

        for (i in list){
            if (exit.size == 0){

                exit.add(i.categoria)

            }else{

                var norepi = true

                for (j in exit){
                    if (j.equals(i.categoria)){

                        norepi = false

                    }
                }

                if (norepi){

                    exit.add(i.categoria)

                }

            }
        }

        return exit

    }

    override fun spinnerProductos(list: ArrayList<Productos>): ArrayList<String> {

        val exit = ArrayList<String>()

        for (i in list){

            exit.add(i.id)

        }

        return exit

    }

    override fun spinnerProductos2(list: ArrayList<Productos>): ArrayList<String> {
        val exit = ArrayList<String>()

        for (i in list){

            exit.add(i.id+" "+i.nombre)

        }

        return exit
    }

}