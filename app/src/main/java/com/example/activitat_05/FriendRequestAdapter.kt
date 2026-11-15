package com.example.activitat_05

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FriendRequestAdapter(
    private val items: List<FriendRequest>,
    private val onItemClick: (FriendRequest) -> Unit
) : RecyclerView.Adapter<FriendRequestHolder>() {

    /**
     * Crea (infla) la vista de la fila i construeix el ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendRequestHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.friend_requests_item, parent, false)
        return FriendRequestHolder(view, onItemClick)
    }

    /**
     * Retorna quants elements hi ha a la llista de dades.
     */
    override fun getItemCount(): Int = items.size

    /**
     * Demana al ViewHolder que mostri les dades de la posició donada.
     */
    override fun onBindViewHolder(holder: FriendRequestHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}