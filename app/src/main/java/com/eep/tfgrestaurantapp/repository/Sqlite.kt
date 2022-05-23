package com.eep.tfgrestaurantapp.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.eep.tfgrestaurantapp.entity.Productos

class Sqlite(context: Context): SQLiteOpenHelper(context, "TFGrestaurant", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE comanda (mesa INTEGER PRIMARY KEY, camarero TEXT, fecha TEXT, productos TEXT)")
        db.execSQL("CREATE TABLE producto (id TEXT PRIMARY KEY, categoria TEXT, nombre TEXT, precio INTEGER)")
        registro(Productos("EM1", "Para empezar", "Empanada criolla", 3.0), db)
        registro(Productos("EM2", "Para empezar", "Empanada de jamón y queso", 3.0), db)
        registro(Productos("Em3", "Para empezar", "Empanada de espinaca", 3.0), db)
        registro(Productos("T1", "Para empezar", "Chorizo criollo", 4.3), db)
        registro(Productos("DP1", "Para empezar", "Delicias", 6.9), db)
        registro(Productos("J1", "Para empezar", "Paleta ibérica", 13.0), db)
        registro(Productos("J3", "Para empezar", "Lomo ibérico", 12.0), db)
        registro(Productos("J4", "Para empezar", "Salchichón", 9.0), db)
        registro(Productos("J5", "Para empezar", "Queso", 10.0), db)
        registro(Productos("J6", "Para empezar", "Pincho polenta", 4.5), db)
        registro(Productos("E1", "Ensaladas", "Rúcula", 8.0), db)
        registro(Productos("E2", "Ensaladas", "Tomate cebolla", 8.0), db)
        registro(Productos("E3", "Ensaladas", "Tomate lechuga aceitunas", 8.0), db)
        registro(Productos("E4", "Ensaladas", "Atún", 12.9), db)
        registro(Productos("V1", "Elaborado a la parrilla", "verduras", 13.5), db)
        registro(Productos("T2", "Elaborado a la parrilla", "Churrasquito", 9.0), db)
        registro(Productos("T3", "Elaborado a la parrilla", "Bife", 11.0), db)
        registro(Productos("T4", "Elaborado a la parrilla", "Solomillo", 12.5), db)
        registro(Productos("T5", "Elaborado a la parrilla", "Máx Bife", 17.0), db)
        registro(Productos("T6", "Elaborado a la parrilla", "Máx Solomillo", 19.5), db)
        registro(Productos("T7", "Elaborado a la parrilla", "Duo", 26.5), db)
        registro(Productos("T8", "Elaborado a la parrilla", "Hamburgesa pampeana", 9.5), db)
        registro(Productos("T9", "Elaborado a la parrilla", "Corte especial", 17.5), db)
        registro(Productos("A1", "Elaborado a la parrilla", "Atún rojo", 22.5), db)
        registro(Productos("B1", "Bocadillos gourmet", "Choripan", 4.3), db)
        registro(Productos("B1c", "Bocadillos gourmet", "Choripan con tomate", 4.6), db)
        registro(Productos("B2", "Bocadillos gourmet", "Churrasquito", 5.5), db)
        registro(Productos("B2c", "Bocadillos gourmet", "Churrasquito con tomate", 6.0), db)
        registro(Productos("B3", "Bocadillos gourmet", "Bife", 7.5), db)
        registro(Productos("B3c", "Bocadillos gourmet", "Bife con tomate", 8.0), db)
        registro(Productos("B4", "Bocadillos gourmet", "Solomillo", 9.0), db)
        registro(Productos("B4c", "Bocadillos gourmet", "Solomillo con tomate", 9.5), db)
        registro(Productos("AP", "Para Acompañar", "Patata asada", 3.5), db)
        registro(Productos("AP1", "Para Acompañar", "Patata fritas", 3.5), db)
        registro(Productos("P1", "Caprichos dulces", "Alfajores", 3.0), db)
        registro(Productos("P2", "Caprichos dulces", "Tarta", 6.0), db)
        registro(Productos("P3", "Caprichos dulces", "Panqueque", 7.5), db)
        registro(Productos("P4", "Caprichos dulces", "Sernik", 6.0), db)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        TODO("Not yet implemented")

    }

    private fun registro(productos: Productos, db: SQLiteDatabase){

        val registro = ContentValues()

        registro.put("id", productos.id)
        registro.put("categoria", productos.categoria)
        registro.put("nombre", productos.nombre)
        registro.put("precio", productos.precio)

        db.insert("producto", null, registro)

    }

}