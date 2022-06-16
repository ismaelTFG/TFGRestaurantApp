package com.eep.tfgrestaurantapp.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FireBase {

    /**
     * metodo para conectar con firebase
     * @return conexion con firebase
     */
    fun getFireBase(): FirebaseFirestore {

        return Firebase.firestore

    }

}