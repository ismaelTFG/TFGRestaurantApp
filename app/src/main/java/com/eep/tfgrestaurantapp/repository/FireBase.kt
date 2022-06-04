package com.eep.tfgrestaurantapp.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FireBase {

    fun getFireBase(): FirebaseFirestore {

        return Firebase.firestore

    }

}