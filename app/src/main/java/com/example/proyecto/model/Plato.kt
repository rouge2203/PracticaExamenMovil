package com.example.proyecto.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
//@Entity(tableName = "lugar")

data class Plato(
    //Clase que mapea una tabla de sqlite
    //@PrimaryKey(autoGenerate = true)
    var id: String,
    //@ColumnInfo(name="nombre")
    val nombre: String,
    //@ColumnInfo(name="correo")
    val precio: String?
    //@ColumnInfo(name="web")
) : Parcelable{
    constructor():
            this("","","")
}

