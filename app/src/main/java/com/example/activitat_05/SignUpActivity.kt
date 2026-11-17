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
import com.google.android.material.textfield.TextInputLayout



class SignUpActivity : AppCompatActivity() {
    private val viewModel: SignUpViewModel by viewModels()
    lateinit var signUp : Button
    lateinit var volverLogIn : TextView
    lateinit var campoNombreCompleto : TextInputLayout
    lateinit var campoUserName : TextInputLayout
    lateinit var campoEmail : TextInputLayout
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
        campoNombreCompleto = findViewById(R.id.fullNameFiled)
        campoUserName = findViewById(R.id.userNameField)
        campoEmail = findViewById(R.id.emailField)
        campoPassword = findViewById(R.id.password)
        campoPasswordAgain = findViewById(R.id.passwordAgain)

        // vID --> end

        // Funciones --> f start
        signUp.setOnClickListener {
            if (viewModel.validator()) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        volverLogIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // f --> end

        campoNombreCompleto.editText?.doAfterTextChanged { texto ->
            viewModel.onNombreChanged(texto?.toString() ?: "")
        }
        campoUserName.editText?.doAfterTextChanged { texto ->
            viewModel.onApellidoChanged(texto?.toString() ?: "")
        }
        campoEmail.editText?.doAfterTextChanged { texto ->
            viewModel.onEmailChanged(texto?.toString() ?: "")
        }
        campoPassword.editText?.doAfterTextChanged { texto ->
            viewModel.onPasswordChanged(texto?.toString() ?: "")
        }
        campoPasswordAgain.editText?.doAfterTextChanged { texto ->
            viewModel.onPasswordAgainChanged(texto?.toString() ?: "")
        }

        viewModel.nombre.observe(this) { valor ->
            if (campoNombreCompleto.editText?.text.toString() != valor.toString()) campoNombreCompleto.editText?.setText(valor.toString())
        }

        viewModel.apellido.observe(this) { valor ->
            if (campoUserName.editText?.text.toString() != valor.toString()) campoUserName.editText?.setText(valor.toString())
        }

        viewModel.email.observe(this) { valor ->
            if (campoEmail.editText?.text.toString() != valor.toString()) campoEmail.editText?.setText(valor.toString())
        }

        viewModel.contrasenya.observe(this) { valor ->
            if (campoPassword.editText?.text.toString() != valor.toString()) campoPassword.editText?.setText(valor.toString())
        }

        viewModel.contrasenyaDos.observe(this) { valor ->
            if (campoPasswordAgain.editText?.text.toString() != valor.toString()) campoPasswordAgain.editText?.setText(valor.toString())
        }

        viewModel.errorNombre.observe(this) { campoNombreCompleto.error = it }
        viewModel.errorApellido.observe(this) { campoUserName.error = it }
        viewModel.errorEmail.observe(this) { campoEmail.error = it }
        viewModel.errorContrasenya.observe(this) { campoPassword.error = it }
        viewModel.errorContrasenyaDos.observe(this) { campoPasswordAgain.error = it }
    }
}