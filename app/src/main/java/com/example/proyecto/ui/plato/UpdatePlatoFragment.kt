package com.example.proyecto.ui.plato

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentUpdatePlatoBinding
import com.example.proyecto.model.Plato
import com.example.proyecto.viewmodel.PlatoViewModel

class UpdatePlatoFragment : Fragment() {

    //recupera argumentos
    private val args by navArgs<UpdatePlatoFragmentArgs>()

    private var _binding: FragmentUpdatePlatoBinding? = null
    private val binding get() = _binding!!
    private lateinit var platoViewModel: PlatoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        platoViewModel = ViewModelProvider(this).get(PlatoViewModel :: class.java)
        _binding = FragmentUpdatePlatoBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        //cargar los valores edit
        binding.etNombre.setText(args.plato.nombre)
        binding.etPrecio.setText(args.plato.precio)

        binding.btUpdate.setOnClickListener{ updatePlato() }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun updatePlato(){
        val nombre = binding.etNombre.text.toString()
        val precio = binding.etPrecio.text.toString()
        if(nombre.isEmpty()){
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_LONG).show()
        }
        else if(precio.isEmpty()){
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_LONG).show()
        }
        else{
            val plato = Plato(args.plato.id,nombre,precio)
            platoViewModel.updatePlato(plato)
            Toast.makeText(requireContext(),getString(R.string.msg_plato_updated),Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updatePlatoFragment_to_nav_plato)
        }
    }

    private fun deletePlato(){
        val builder=AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)){_,_ ->
            platoViewModel.deletePlato(args.plato)
            Toast.makeText(requireContext(),
            getString(R.string.deleted)+" ${args.plato.nombre}!",
            Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updatePlatoFragment_to_nav_plato)
        }
        builder.setNegativeButton(getString(R.string.no)) {_,_->}
        builder.setTitle(R.string.deleted)
        builder.setMessage(getString(R.string.seguroBorrar)+" ${args.plato.nombre}?")
        builder.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //si es borrado...
        if(item.itemId==R.id.menu_delete){
            deletePlato()
        }
        return super.onOptionsItemSelected(item)
    }
}