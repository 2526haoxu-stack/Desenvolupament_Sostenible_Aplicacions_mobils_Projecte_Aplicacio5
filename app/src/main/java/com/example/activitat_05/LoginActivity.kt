package com.example.activitat_05

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activitat_05.loginViewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var buttonEnter : Button
    lateinit var signUp : TextView
    lateinit var user: EditText
    lateinit var pass: EditText

    private val viewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        user = findViewById(R.id.inputUser)
        pass = findViewById(R.id.inputPass)

        viewModel.errorUser.observe(this) { user.error = it }
        viewModel.errorPass.observe(this) { pass.error = it }

        viewModel.loginTrue.observe(this) { event ->
            if (event == LoginViewModel.logEvents.OK) {
                Toast.makeText(this, "LOGIN CORRECTO", Toast.LENGTH_SHORT).show()
                viewModel.resetEvent()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Relacionar variables con los id's --> Start
        buttonEnter = findViewById(R.id.aMEnter)
        signUp = findViewById(R.id.aMSignUp)

        // rV --> End

        // Funciones --> Start (ESTAN AHORA EN PRINCIPIO)
        buttonEnter.setOnClickListener {
            viewModel.login(
                user.text.toString(),
                pass.text.toString()
            )
        }

        signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}