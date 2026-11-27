package com.example.activitat_05.viewModels

import androidx.lifecycle.ViewModel
import com.example.activitat_05.DataSourceRecetas
import com.example.activitat_05.Receta

class RecetaViewModel : ViewModel() {
    val recetasList : MutableList<Receta> = DataSourceRecetas.recetas

    private var currentQuery: String = ""

    // Filtra la lista combinando el texto de búsqueda
    fun filter(query: String? = currentQuery): List<Receta> {
        currentQuery = query ?: ""
        val lowerQuery = currentQuery.lowercase().trim()

        return recetasList.filter { receta ->
            val matchesText = receta.name.lowercase().contains(lowerQuery)
            matchesText
        }
    }
}