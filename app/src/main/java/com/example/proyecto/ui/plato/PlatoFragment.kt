package com.example.proyecto.ui.plato

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.R
import com.example.proyecto.adapter.PlatoAdapter
import com.example.proyecto.databinding.FragmentPlatoBinding
import com.example.proyecto.viewmodel.PlatoViewModel

class PlatoFragment : Fragment() {

    private lateinit var  platoViewModel: PlatoViewModel
    private var _binding: FragmentPlatoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        platoViewModel =
            ViewModelProvider(this)[PlatoViewModel::class.java]
        _binding = FragmentPlatoBinding.inflate(inflater, container, false)

        binding.fbAgregar.setOnClickListener{
            findNavController().navigate(R.id.action_nav_plato_to_addPlatoFragment)  }

        //Cargar datos
        val platoAdapter = PlatoAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter  = platoAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        platoViewModel.getAllData.observe(viewLifecycleOwner){
                platos -> platoAdapter.setPlatos(platos)
        }




        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}