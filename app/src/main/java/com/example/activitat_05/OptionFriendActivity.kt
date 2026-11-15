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

class OptionFriendActivity : AppCompatActivity() {
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
                items = firend,
                onItemClick = { item ->
                    // AQUÍ gestionem el clic: mostrem un Toast amb el títol
                    Toast.makeText(
                        this,
                        "Has clicat: ${item.name}",
                        Toast.LENGTH_SHORT
                    ).show()
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
                onItemClick = { item ->
                    // AQUÍ gestionem el clic: mostrem un Toast amb el títol
                    Toast.makeText(
                        this,
                        "Has clicat: ${item.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )

            // 5. Assignar l'Adapter al RecyclerView
            recyclerViewRequest.adapter = adapterRequest

        // Codigo de rV de Friend Item --> End
    }
}