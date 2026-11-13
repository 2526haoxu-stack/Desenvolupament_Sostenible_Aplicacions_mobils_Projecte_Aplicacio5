package com.example.activitat_05

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity()/*, firstFragment.Listener*/ {
    lateinit var aMToolbar : Toolbar
    lateinit var bottomNav : BottomNavigationView

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
        bottomNav = findViewById(R.id.bottom_navigation)
        // rV --> End

        // Funciones --> Start
        setSupportActionBar(aMToolbar)

        bottomNav.setOnItemSelectedListener { item ->
            val selectedFragment: Fragment? = when (item.itemId) {
                R.id.help_fragment -> HelpFragment()
                R.id.home_fragment -> firstFragment()
                R.id.profile_fragment -> ProfileFragment()
                R.id.my_recipe_fragment -> MyRecipeFragment()
                else -> null
            }

            // Realitza la transacció de Fragment manualment
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, selectedFragment)
                    .commit()
            }
            true // Indica que la selecció ha estat gestionada
        }

        // f --> End
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
