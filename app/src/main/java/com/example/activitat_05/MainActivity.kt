package com.example.activitat_05

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()/*, firstFragment.Listener*/ {
    lateinit var aMToolbar : Toolbar

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
        aMToolbar = findViewById(R.id.my_toolbar)

        // rV --> End

        // Funciones --> Start
        setSupportActionBar(aMToolbar)

        // f --> End

        // Funciones para navegar entre los fragmentos --> Start
        findViewById<LinearLayout>(R.id.aMHelp).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, HelpFragment())
                .addToBackStack(null)
                .commit()
        }

        findViewById<LinearLayout>(R.id.aMHome).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, firstFragment())
                .addToBackStack(null)
                .commit()
        }

        findViewById<LinearLayout>(R.id.aMProfile).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, ProfileFragment())
                .addToBackStack(null)
                .commit()
        }

        findViewById<LinearLayout>(R.id.aMMyRecipe).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, MyRecipeFragment())
                .addToBackStack(null)
                .commit()
        }
        // fNF --> End

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_appbar, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Has entrado a la configuración", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.changeCount -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Has cerrado la sesión para cambiar de cuenta", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.signOut -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Has cerrado la sesión", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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