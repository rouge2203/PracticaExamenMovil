package com.example.proyecto.repository

import androidx.lifecycle.MutableLiveData
import com.example.proyecto.data.PlatoDao
import com.example.proyecto.model.Plato

class PlatoRepository (private val platoDao: PlatoDao) {
    val getAllData: MutableLiveData<List<Plato>> = platoDao.getPlatos()

    fun addPlato(plato: Plato) {
        platoDao.savePlato(plato)
    }

    fun updatePlato(plato: Plato) {
        platoDao.savePlato(plato)
    }

    fun deletePlato(plato: Plato) {
        platoDao.deletePlato(plato)
    }

}