package com.example.activitat_05

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), firstFragment.Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Fragment inicial (solo la primera vez, no al girar la pantalla)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, firstFragment())
                .commit()
        }
    }

    // La Activity decide qué hacer cuando el fragment termina
    override fun onFragmentFinalitzat() {
//        supportFragmentManager.beginTransaction()
//            .setReorderingAllowed(true)
//            .replace(R.id.fragmentContainerView, secondFragment())
//            .addToBackStack(null)
//            .commit()
    }
}