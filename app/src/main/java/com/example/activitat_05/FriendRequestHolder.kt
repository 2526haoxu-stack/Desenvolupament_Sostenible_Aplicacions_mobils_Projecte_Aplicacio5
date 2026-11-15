package com.example.activitat_05

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendRequestHolder(
    itemView: View,
    private val onItemClick: (FriendRequest) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val requestName: TextView = itemView.findViewById(R.id.requestName)
    private val requestUserName: TextView = itemView.findViewById(R.id.requestUserName)

    /**
     * Actualitza les vistes de la fila amb les dades de MyItem
     * i configura els listeners d'esdeveniments.
     */
    fun bind(item: FriendRequest) {
        requestName.text = item.name
        requestUserName.text = item.userName

        // Exemple de gestió de clic sobre tota la fila
        itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}