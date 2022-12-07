package com.example.proyecto.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.proyecto.data.PlatoDao
import com.example.proyecto.model.Plato
import com.example.proyecto.repository.PlatoRepository

class PlatoViewModel(application: Application)
    : AndroidViewModel(application){

    val getAllData : MutableLiveData<List<Plato>>

    private val repository: PlatoRepository = PlatoRepository(PlatoDao())

    init {
        getAllData = repository.getAllData
    }

    fun addPlato(plato: Plato) {
        repository.addPlato(plato)
    }

    fun updatePlato(plato: Plato) {
        repository.updatePlato(plato)
    }

    fun deletePlato(plato: Plato) {
        repository.deletePlato(plato)
    }

    

}