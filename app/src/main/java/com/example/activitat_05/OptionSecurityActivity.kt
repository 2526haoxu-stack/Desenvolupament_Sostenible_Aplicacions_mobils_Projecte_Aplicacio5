package com.example.activitat_05

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OptionSecurityActivity : AppCompatActivity() {
    lateinit var close : LinearLayout
    lateinit var buttonAccept : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_option_security)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Relacionar variables con los id's --> Start
        close = findViewById(R.id.aOSClose)
        buttonAccept = findViewById(R.id.aOSAccept)

        // rV --> End

        // Funciones --> Start
        close.setOnClickListener {
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        buttonAccept.setOnClickListener {
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        // f --> End
    }
}