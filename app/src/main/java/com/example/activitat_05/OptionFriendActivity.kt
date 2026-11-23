package com.example.activitat_05

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.activity.viewModels
import com.example.activitat_05.viewModels.FriendItemViewModel

class OptionFriendActivity : AppCompatActivity() {
    private val viewModel: FriendItemViewModel by viewModels()
    lateinit var buttonBack : LinearLayout
    private lateinit var recyclerViewItem: RecyclerView
    private lateinit var adapterItem: FriendItemAdapter
    private lateinit var recyclerViewRequest : RecyclerView
    private lateinit var adapterRequest: FriendRequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_option_friend)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Relacionar variables con los id's --> Start
        buttonBack = findViewById(R.id.aOFClose)
        // rV --> End

        // Funciones --> Start
        buttonBack.setOnClickListener {
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        // f --> End

        // Codigos de RecyclerView de Friend Item --> Start
        // La de chatear
            // 1. Obtenir referència al RecyclerView del layout
            recyclerViewItem = findViewById(R.id.friendItemRecyclerView)

            // 2. Configurar LayoutManager (com es col·loquen les files)
            recyclerViewItem.layoutManager = LinearLayoutManager(this)

            // 3. Crear llista de dades (des de DataSource o directament)
            val firend = DataSourceFriend.friends

            adapterItem = FriendItemAdapter(
                items = viewModel.friendsList,
                onItemClick = { item ->
                    eliminarAmigo(item)
                }
            )

            // 5. Assignar l'Adapter al RecyclerView
            recyclerViewItem.adapter = adapterItem

        // La de solicitudes de amistad
            // 1. Obtenir referència al RecyclerView del layout
            recyclerViewRequest = findViewById(R.id.friendRequestsRecyclerView)

            // 2. Configurar LayoutManager (com es col·loquen les files)
            recyclerViewRequest.layoutManager = LinearLayoutManager(this)

            // 3. Crear llista de dades (des de DataSource o directament)
            val request = DataSourceFriendRequest.requests
            adapterRequest = FriendRequestAdapter(
                items = request,
                onAcceptClick = { item ->
                    aceptarSolicitud(item)
                },
                onRejectClick = { item ->
                    rechazarSolicitud(item)
                }
            )

            // 5. Assignar l'Adapter al RecyclerView
            recyclerViewRequest.adapter = adapterRequest
        // Codigo de rV de Friend Item --> End
    }

    // Elimina un amigo llamando al ViewModel y notificando al adapter
    private fun eliminarAmigo(amigo : FriendItem) {
        val position = viewModel.removeFriend(amigo)
        if (position != -1) {
            adapterItem.notifyItemRemoved(position)
            Toast.makeText(this, "Eliminado: ${amigo.name}", Toast.LENGTH_SHORT).show()
        }
    }

    // Acepta una solicitud, elimina esa solicitud de la lista de solicitudes y añade ese amigo dentro de la lista de amigos
    private fun aceptarSolicitud(request: FriendRequest) {
        val (reqPosition, friendInsertedPosition) = viewModel.aceptarSolicitud(request)
        if (reqPosition != -1 && friendInsertedPosition != -1) {
            adapterRequest.notifyItemRemoved(reqPosition)
            adapterItem.notifyItemInserted(friendInsertedPosition)
            Toast.makeText(this, "Aceptada solicitud de: ${request.name}", Toast.LENGTH_SHORT).show()
        }
    }

    // Rechaza una solicitud, elimina esa solicitud de la lista de solicitudes
    private fun rechazarSolicitud(request: FriendRequest) {
        val reqPosition = viewModel.rechazarSolicitud(request)
        if (reqPosition != -1) {
            adapterRequest.notifyItemRemoved(reqPosition)
            Toast.makeText(this, "Rechazada solicitud de: ${request.name}", Toast.LENGTH_SHORT).show()
        }
    }
}