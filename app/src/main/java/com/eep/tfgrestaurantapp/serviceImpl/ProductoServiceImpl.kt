package com.eep.tfgrestaurantapp.serviceImpl

import android.content.ContentValues
import android.content.Context
import com.eep.tfgrestaurantapp.entity.Productos
import com.eep.tfgrestaurantapp.repository.Sqlite
import com.eep.tfgrestaurantapp.service.ProductoService
import java.util.ArrayList

class ProductoServiceImpl(context: Context): ProductoService {

    //conexion con sqlite
    val sqlite = Sqlite(context)

    /**
     * metodo para mostrar todos los productos
     * @return lista de los productos
     */
    override fun listAll(): ArrayList<Productos> {

        val db = sqlite.writableDatabase
        val fila = db.rawQuery("SELECT * FROM producto", null)
        val productos = ArrayList<Productos>()

        if (fila!!.moveToFirst()){

            while (fila.isAfterLast == false){

                val id = fila.getString(0)
                val categoria = fila.getString(1)
                val nombre = fila.getString(2)
                val precio = fila.getDouble(3)

                productos.add(Productos(id, categoria, nombre, precio))

                fila.moveToNext()

            }

        }

        return productos

    }

    /**
     * metodo para añadir productos
     * @param productos
     */
    override fun add(productos: Productos) {

        val db = sqlite.writableDatabase
        val registro = ContentValues()

        registro.put("id", productos.id)
        registro.put("categoria", productos.categoria)
        registro.put("nombre", productos.nombre)
        registro.put("precio", productos.precio)

        db.insert("producto", null, registro)

    }

    /**
     * metodo para eliminar productos
     * @param id del producto
     */
    override fun delete(id: String) {

        val db = sqlite.writableDatabase

        db.delete("producto", "id='${id}'", null)
        db.close()

    }

    /**
     * metodo para buscar productos por categoria
     * @param categoria
     * @return lista de productos
     */
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

    /**
     * metodo para mostrar los productos con un formato
     * @param list de los productos
     * @return string
     */
    override fun viewProductos(list: ArrayList<Productos>): String {

        var exit = ""

        for (i in list){

            exit = exit+i.toString()+"\n\n"

        }

        return exit

    }

    /**
     * validar los productos
     * @param productos
     * @return si el formato es correcto o no
     */
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

    /**
     * metodo para mostrar la categorias de los productos pasados
     * @param list de productos
     * @return lista de las categorias
     */
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

    /**
     * metodo para mostrar los productos en el spinner
     * @param list de los productos
     * @return lista de los productos para mostrar en el spinner
     */
    override fun spinnerProductos(list: ArrayList<Productos>): ArrayList<String> {

        val exit = ArrayList<String>()

        for (i in list){

            exit.add(i.id)

        }

        return exit

    }

    /**
     * metodo para mostrar los productos en el spinner con otro formato
     * @param list de los productos
     * @return lista de los productos para mostrar en el spinner
     */
    override fun spinnerProductos2(list: ArrayList<Productos>): ArrayList<String> {
        val exit = ArrayList<String>()

        for (i in list){

            exit.add(i.id+" "+i.nombre)

        }

        return exit
    }

}