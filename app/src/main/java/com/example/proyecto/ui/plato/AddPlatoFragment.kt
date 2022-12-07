package com.example.proyecto.ui.plato

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentAddPlatoBinding
import com.example.proyecto.viewmodel.PlatoViewModel
import com.example.proyecto.model.Plato

class AddPlatoFragment : Fragment() {

    private var _binding: FragmentAddPlatoBinding? = null
    private val binding get() = _binding!!
    private lateinit var platoViewModel: PlatoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        platoViewModel = ViewModelProvider(this).get(PlatoViewModel::class.java)
        _binding = FragmentAddPlatoBinding.inflate(inflater, container, false)

        binding.btAgregar.setOnClickListener { addPlato() }

        return binding.root
    }

    //Efectivamente hace el registro del plato en la base de datos
    private fun addPlato() {
        val nombre=binding.etNombre.text.toString()

        if (nombre.isNotEmpty()) {   //Al menos tenemos un nombre
            val nombre=binding.etNombre.text.toString()
            val precio=binding.etPrecio.text.toString()

            val plato = Plato("",nombre,precio)
            platoViewModel.addPlato(plato)
            Toast.makeText(requireContext(),getString(R.string.msg_plato_added),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addPlatoFragment_to_nav_plato)
        } else {  //No hay info del plato...
            Toast.makeText(requireContext(),getString(R.string.msg_data),
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

