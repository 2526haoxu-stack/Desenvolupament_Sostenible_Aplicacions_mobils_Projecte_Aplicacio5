package com.example.activitat_05

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView // Search
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.example.activitat_05.viewModels.RecetaViewModel
import com.google.android.material.chip.ChipGroup
import androidx.core.view.isVisible

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [firstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class firstFragment : Fragment(R.layout.fragment_first) {
    private val viewModel: RecetaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val adapter = Adapter(
            DataSourceRecetas.recetas
        ) { receta ->
            Toast.makeText(
                requireContext(),
                receta.name,
                Toast.LENGTH_SHORT
            ).show()
        }
        recyclerView.adapter = adapter

        // Configuración del SearchView --> Start
        val searchView = view.findViewById<SearchView>(R.id.fFragmentSearch)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = viewModel.filter(newText)
                adapter.updateList(filteredList)
                return true
            }
        })
        // CDS --> End

        val filterIcon = view.findViewById<ImageView>(R.id.fFragmentFilter)
        val filterPanel = view.findViewById<View>(R.id.filterPanel)
        val container = view.findViewById<ViewGroup>(R.id.filterPanel).parent as ViewGroup
        val chipGroup = view.findViewById<ChipGroup>(R.id.chipGroupCategoria)

        // Mostrar / ocultar con animación
        filterIcon.setOnClickListener {
            TransitionManager.beginDelayedTransition(container)
            filterPanel.visibility =
                if (filterPanel.isVisible) View.GONE else View.VISIBLE
        }

        view.findViewById<View>(R.id.btnAplicar).setOnClickListener {
            val categoria = when (chipGroup.checkedChipId) {
                R.id.chipJaponesa -> "Japonesa"
                R.id.chipEspañola -> "Española"
                R.id.chipItaliana -> "Italiana"
                R.id.chipUcraniana -> "Ucraniana"
                R.id.chipGriega -> "Griega"
                R.id.chipArmenia -> "Armenia"
                R.id.chipVegetal -> "Vegetal"
                R.id.chipAnimal -> "Animal"
                else -> null // Todas
            }
            aplicarFiltro(categoria)

            TransitionManager.beginDelayedTransition(container)
            filterPanel.visibility = View.GONE
        }

        view.findViewById<View>(R.id.btnLimpiar).setOnClickListener {
            chipGroup.check(R.id.chipTodas)
            aplicarFiltro(null)
        }
    }

    private fun aplicarFiltro(categoria: String?) {
        // Aquí filtras tu lista y actualizas el adapter, por ejemplo:
        // val filtradas = if (categoria == null) todasLasRecetas
        //                 else todasLasRecetas.filter { it.categoria == categoria }
        // adapter.submitList(filtradas)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            firstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}