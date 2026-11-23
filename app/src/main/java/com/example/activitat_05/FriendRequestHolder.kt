package com.example.activitat_05

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendRequestHolder(
    itemView: View,
    private val onAcceptClick: (FriendRequest) -> Unit,
    private val onRejectClick: (FriendRequest) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val requestName: TextView = itemView.findViewById(R.id.requestName)
    private val requestUserName: TextView = itemView.findViewById(R.id.requestUserName)
    private val btnAceptar: ImageView = itemView.findViewById(R.id.aceptar)
    private val btnRechazar: ImageView = itemView.findViewById(R.id.rechazar)


    /**
     * Actualitza les vistes de la fila amb les dades de MyItem
     * i configura els listeners d'esdeveniments.
     */
    fun bind(item: FriendRequest) {
        requestName.text = item.name
        requestUserName.text = item.userName

        // Listener para aceptar la solicitud
        btnAceptar.setOnClickListener {
            onAcceptClick(item)
        }
        // Listener para rechazar la solicitud
        btnRechazar.setOnClickListener {
            onRejectClick(item)
        }
    }
}