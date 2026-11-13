package com.example.activitat_05
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ViewHolder(
    itemView: View,
    private val onItemClick: (Receta) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val tvItemName: TextView =
        itemView.findViewById(R.id.itemName)
    private val ivItemImage: ImageView =
        itemView.findViewById(R.id.itemImage)
    private val btItemOpen: ImageButton =
        itemView.findViewById(R.id.itemOpen)

    fun mostrarReceta(receta: Receta) {
        tvItemName.text = receta.name

        ivItemImage.setImageResource(receta.image)

        btItemOpen.setOnClickListener { onItemClick(receta) }
    }
}
