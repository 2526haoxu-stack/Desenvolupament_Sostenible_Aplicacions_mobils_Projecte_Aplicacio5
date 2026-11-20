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
import androidx.core.widget.doAfterTextChanged
import com.example.activitat_05.viewModels.SignUpViewModel
import com.example.activitat_05.viewModels.SignUpState
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class SignUpActivity : AppCompatActivity() {
    private val viewModel: SignUpViewModel by viewModels()
    lateinit var signUp : Button
    lateinit var volverLogIn : TextView

    // Donde escribe el usuario
    lateinit var campoNombreCompleto : TextInputEditText
    lateinit var campoUserName : TextInputEditText
    lateinit var campoEmail : TextInputEditText
    lateinit var campoPassword : TextInputEditText
    lateinit var campoPasswordAgain : TextInputEditText

    // Contenedores, donde se muestra el error
    lateinit var errorNombreCompleto : TextInputLayout
    lateinit var errorUserName : TextInputLayout
    lateinit var errorEmail : TextInputLayout
    lateinit var errorPassword : TextInputLayout
    lateinit var errorPasswordAgain : TextInputLayout

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
        campoNombreCompleto = findViewById(R.id.fullNameInput)
        campoUserName = findViewById(R.id.userNameInput)
        campoEmail = findViewById(R.id.emailInput)
        campoPassword = findViewById(R.id.passwordInput)
        campoPasswordAgain = findViewById(R.id.passwordAgainInput)
        errorNombreCompleto = findViewById(R.id.fullNameFiled)
        errorUserName = findViewById(R.id.userNameField)
        errorEmail = findViewById(R.id.emailField)
        errorPassword = findViewById(R.id.password)
        errorPasswordAgain = findViewById(R.id.passwordAgain)

        // ---------- Campo -> ViewModel ----------
        campoNombreCompleto.doAfterTextChanged { viewModel.onNombreChanged(it.toString()) }
        campoUserName.doAfterTextChanged { viewModel.onNombreUsuarioChanged(it.toString()) }
        campoEmail.doAfterTextChanged { viewModel.onEmailChanged(it.toString()) }
        campoPassword.doAfterTextChanged { viewModel.onPasswordChanged(it.toString()) }
        campoPasswordAgain.doAfterTextChanged { viewModel.onPasswordAgainChanged(it.toString()) }

        // vID --> end

        // Funciones --> f start
        signUp.setOnClickListener {
            viewModel.registrar()
        }

        volverLogIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // f --> end

        viewModel.state.observe(this) { state ->
            when (state) {
                is SignUpState.Idle -> {
                    // No hacer nada
                }
                is SignUpState.Success -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        viewModel.error.observe(this) { error ->
            errorNombreCompleto.error = error.nombre
            errorUserName.error = error.nombreUsuario
            errorEmail.error = error.email
            errorPassword.error = error.contrasenya
            errorPasswordAgain.error = error.contrasenyaDos
        }
    }
}