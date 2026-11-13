package com.example.activitat_05

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendItemHolder(
    itemView: View,
    private val onItemClick: (FriendItem) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val name : TextView = itemView.findViewById(R.id.friendName)
    private val userName : TextView = itemView.findViewById(R.id.friendUserName)

    /**
     * Actualitza les vistes de la fila amb les dades de MyItem
     * i configura els listeners d'esdeveniments.
     */
    fun bind(item: FriendItem) {
        name.text = item.name
        userName.text = item.userName

        // Exemple de gestió de clic sobre tota la fila
        itemView.setOnClickListener {
            onItemClick(item)
        }
    }

}