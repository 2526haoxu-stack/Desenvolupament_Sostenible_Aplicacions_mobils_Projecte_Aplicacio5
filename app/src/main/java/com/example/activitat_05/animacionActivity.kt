package com.example.activitat_05

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class animacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_animacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.iv_icon)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cargar y aplicar animación
        val anim = AnimationUtils.loadAnimation(this, R.anim.animacion)
        findViewById<android.widget.ImageView>(R.id.iv_icon).startAnimation(anim)

        // Esperar y pasar a la pantalla principal
        val tiempoEspera = 1200L // milisegundos
        android.os.Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, tiempoEspera)
    }
}