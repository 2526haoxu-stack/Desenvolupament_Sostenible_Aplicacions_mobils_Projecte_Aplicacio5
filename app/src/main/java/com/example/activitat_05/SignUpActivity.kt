package com.example.activitat_05

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activitat_05.viewModels.SignUpViewModel
import com.google.android.material.textfield.TextInputLayout



class SignUpActivity : AppCompatActivity() {
    private val viewModel: SignUpViewModel by viewModels()
    lateinit var signUp : Button
    lateinit var volverLogIn : TextView
    lateinit var campoNombre : TextInputLayout
    lateinit var campoApellido : TextInputLayout
    lateinit var campoEmail : TextInputLayout
    lateinit var campoBirthday : TextInputLayout
    lateinit var campoPhoneNumber : TextInputLayout
    lateinit var campoPassword : TextInputLayout
    lateinit var campoPasswordAgain : TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Vincular las variables con el ID --> vID start
        signUp = findViewById(R.id.aSSignUpButton)
        volverLogIn = findViewById(R.id.aSBackLogIn)
        campoNombre = findViewById(R.id.nameFiled)
        campoApellido = findViewById(R.id.lastNameField)
        campoEmail = findViewById(R.id.emailField)
        campoBirthday = findViewById(R.id.birthday)
        campoPhoneNumber = findViewById(R.id.phoneNumber)
        campoPassword = findViewById(R.id.password)
        campoPasswordAgain = findViewById(R.id.passwordAgain)

        // vID --> end

        // Funciones --> f start
        signUp.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        volverLogIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // f --> end

        viewModel.user.observe(this) { valor ->
            campoNombre.editText?.setText(valor.nombre)
            campoApellido.editText?.setText(valor.apellido)
            campoEmail.editText?.setText(valor.email)
            campoBirthday.editText?.setText(valor.fechaNacimiento)
            campoPhoneNumber.editText?.setText(valor.telefono)
            campoPassword.editText?.setText(valor.contrasenya)
            campoPasswordAgain.editText?.setText(valor.contrasenyaDos)
        }
    }
}