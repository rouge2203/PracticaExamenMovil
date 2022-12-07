package com.example.proyecto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.databinding.PlatoFilaBinding
import com.example.proyecto.model.Plato
import com.example.proyecto.ui.plato.PlatoFragmentDirections

class PlatoAdapter: RecyclerView.Adapter<PlatoAdapter.PlatoViewHolder>() {

    private var listarPlatos = emptyList<Plato>()

    inner class PlatoViewHolder(private var itemBinding: PlatoFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root){

    fun dibujar(plato: Plato) {
        itemBinding.tvNombre.text = plato.nombre
        itemBinding.tvPrecio.text = plato.precio

        //evento edit
        itemBinding.vistaFila.setOnClickListener {
        val accion = PlatoFragmentDirections
            .actionNavPlatoToUpdatePlatoFragment(plato)
        itemView.findNavController().navigate(accion)
        }

    }
}

    fun setPlatos(platos: List<Plato>){
        listarPlatos = platos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatoViewHolder{
        val itemBinding = PlatoFilaBinding
            .inflate(LayoutInflater.from(parent.context)
            ,parent,false)
        return PlatoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PlatoViewHolder, position: Int) {
        val plato = listarPlatos[position]
        holder.dibujar(plato)
    }

    override fun getItemCount(): Int{
        return listarPlatos.size
    }
//listado de platoa p;ciommo
}