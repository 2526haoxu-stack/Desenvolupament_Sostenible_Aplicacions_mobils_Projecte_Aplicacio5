package com.example.activitat_05

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()/*, firstFragment.Listener*/ {
    lateinit var buttonSetting : ImageView

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

        // Relacionar las variables con los id's --> Start
        buttonSetting = findViewById(R.id.aMSetting)

        // rV --> End

        // Funciones --> Start
        buttonSetting.setOnClickListener {
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        // f --> End

        // Funciones para navegar entre los fragmentos --> Start
        // Ir al fragmento de Help
        findViewById<LinearLayout>(R.id.aMHelp).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, HelpFragment())
                .addToBackStack(null)
                .commit()
        }

        // Ir al fragmento principal
        findViewById<LinearLayout>(R.id.aMHome).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, firstFragment())
                .addToBackStack(null)
                .commit()
        }

        // Ir al fragmento de Perfil
        findViewById<LinearLayout>(R.id.aMProfile).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, ProfileFragment())
                .addToBackStack(null)
                .commit()
        }

        // Ir al fragmento de Mi receta
        findViewById<LinearLayout>(R.id.aMMyRecipe).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, MyRecipeFragment())
                .addToBackStack(null)
                .commit()
        }
        // fNF --> End

    }



    // La Activity decide qué hacer cuando el fragment termina
//    override fun onFragmentFinalitzat() {
//        supportFragmentManager.beginTransaction()
//            .setReorderingAllowed(true)
//            .replace(R.id.fragmentContainerView, HelpFragment())
//            .addToBackStack(null)
//            .commit()
//    }
}