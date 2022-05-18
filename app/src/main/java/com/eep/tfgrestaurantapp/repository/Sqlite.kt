package com.eep.tfgrestaurantapp.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Sqlite(context: Context): SQLiteOpenHelper(context, "TFGrestaurant", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE comanda (mesa INTEGER PRIMARY KEY, camarero TEXT, fecha TEXT, productos TEXT)")
        db.execSQL("CREATE TABLE producto (id INTEGER PRIMARY KEY, categorio TEXT, nombre TEXT, precio INTEGER)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        TODO("Not yet implemented")

    }

}