package com.example.instagram2.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object ConfigFirebase {

    private var referencialFirebase: DatabaseReference? = null
    private var referencialAutenticacao: FirebaseAuth? = null


    fun getFirebaseDatabase(): DatabaseReference {
        if (referencialFirebase == null) {
            referencialFirebase = FirebaseDatabase.getInstance().reference
        }
        return referencialFirebase!!
    }

    fun getFirebaseAuth(): FirebaseAuth {
        if (referencialAutenticacao == null) {
            referencialAutenticacao = FirebaseAuth.getInstance()
        }
        return referencialAutenticacao!!
    }



}