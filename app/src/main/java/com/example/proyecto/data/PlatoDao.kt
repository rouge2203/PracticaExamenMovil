package com.example.proyecto.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.proyecto.model.Plato
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
//import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase


class PlatoDao {

    //Firebase var
    private var codigoUsuario: String
    private var firestore: FirebaseFirestore

    init{
        val usuario = Firebase.auth.currentUser?.email
        codigoUsuario = "$usuario"
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun savePlato(plato: Plato){
        val document: DocumentReference
        if(plato.id.isEmpty()){
            //agregar
            document = firestore.
                    collection("platosApp").
                    document(codigoUsuario).
                    collection("misPlatos").
                    document()
            plato.id = document.id
        }else{
            //modificar
            document = firestore.
            collection("platosApp").
            document(codigoUsuario).
            collection("misPlatos").
            document(plato.id)
        }
        val set = document.set(plato)
            set.addOnSuccessListener{
                Log.d("savePlato","Guardado con exito")
            }
            .addOnCanceledListener {
                Log.e("savePlato","Error al guardar")
            }
    }

    fun deletePlato(plato: Plato){
        if(plato.id.isNotEmpty()){
            firestore.
                collection("platosApp").
                document(codigoUsuario).
                collection("misPlatos").
                document(plato.id).
                delete()
                .addOnSuccessListener{
                    Log.d("deletePlato","Eliminado con exito")
                }
                .addOnCanceledListener {
                    Log.d("deletePlato","Error al eliminar")
                }
        }
    }

    fun getPlatos() : MutableLiveData<List<Plato>> {
        val listaPlatos = MutableLiveData<List<Plato>>()
        firestore.
            collection("platosApp").
            document(codigoUsuario).
            collection("misPlatos").
            addSnapshotListener{ snapshot, e ->
                if(e != null){
                    return@addSnapshotListener
                }
                if(snapshot != null) {
                    val lista = ArrayList<Plato>()
                    val platos = snapshot.documents
                    platos.forEach {
                        val plato = it.toObject(Plato ::class.java)
                        if (plato!=null) {
                            lista.add(plato)
                        }
                    }
                    listaPlatos.value = lista
                }
            }
        return listaPlatos
    }
}