package com.example.activitat_05

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FriendItemAdapter(
    private val items: List<FriendItem>,
    private val onItemClick: (FriendItem) -> Unit
) : RecyclerView.Adapter<FriendItemHolder>() {

    /**
     * Crea (infla) la vista de la fila i construeix el ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.friend_item, parent, false)
        return FriendItemHolder(view, onItemClick)
    }

    /**
     * Retorna quants elements hi ha a la llista de dades.
     */
    override fun getItemCount(): Int = items.size

    /**
     * Demana al ViewHolder que mostri les dades de la posició donada.
     */
    override fun onBindViewHolder(holder: FriendItemHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

}