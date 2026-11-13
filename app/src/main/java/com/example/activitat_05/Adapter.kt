package com.example.activitat_05

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val recetas: MutableList<Receta>,
    private val onItemClick: (Receta) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)


        return ViewHolder(view, onItemClick)
    }

    override fun getItemCount(): Int { return recetas.size }

    override fun onBindViewHolder( //Muestra la receta escogida
        holder: ViewHolder,
        index: Int
    ) {
        val receta = recetas[index]
        holder.mostrarReceta(receta)
    }
}