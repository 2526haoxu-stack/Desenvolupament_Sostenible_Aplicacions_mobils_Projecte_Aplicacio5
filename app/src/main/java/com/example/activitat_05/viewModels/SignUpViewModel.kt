package com.example.activitat_05.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns

sealed class SignUpState {
    object Idle : SignUpState()
    object Success : SignUpState()
}

data class Errores(
    val nombre: String?,
    val nombreUsuario: String?,
    val email: String?,
    val contrasenya: String?,
    val contrasenyaDos: String?
)

class SignUpViewModel : ViewModel() {
    // ---------- Campos ----------
    private val _nombreCompleto = MutableLiveData("")
    private val _nombreUsuario = MutableLiveData("")
    private val _email = MutableLiveData("")
    private val _contrasenya = MutableLiveData("")
    private val _contrasenyaDos = MutableLiveData("")

    // ---------- Errores ----------
    private val _errorNombreCompleto = MutableLiveData<String?>()
    private val _errorNombreUsuario = MutableLiveData<String?>()
    private val _errorEmail = MutableLiveData<String?>()
    private val _errorContrasenya = MutableLiveData<String?>()
    private val _errorContrasenyaDos = MutableLiveData<String?>()

    // ---------- Estado general de la pantalla ----------
    private val _state = MutableLiveData<SignUpState>(SignUpState.Idle)
    private val _error = MutableLiveData<Errores>()

    val nombreCompleto: LiveData<String> = _nombreCompleto
    val nombreUsuario: LiveData<String> = _nombreUsuario
    val email: LiveData<String> = _email
    val contrasenya: LiveData<String> = _contrasenya
    val contrasenyaDos: LiveData<String> = _contrasenyaDos

    val errorNombreCompleto: LiveData<String?> = _errorNombreCompleto
    val errorNombreUsuario: LiveData<String?> = _errorNombreUsuario
    val errorEmail: LiveData<String?> = _errorEmail
    val errorContrasenya: LiveData<String?> = _errorContrasenya
    val errorContrasenyaDos: LiveData<String?> = _errorContrasenyaDos

    val state: LiveData<SignUpState> = _state
    val error: LiveData<Errores> = _error

    // ---------- Cambios en los campos ----------
    fun onNombreChanged(nuevoNombre: String) { _nombreCompleto.value = nuevoNombre }
    fun onNombreUsuarioChanged(nuevoNombreUsuario: String) { _nombreUsuario.value = nuevoNombreUsuario }
    fun onEmailChanged(nuevoEmail: String) { _email.value = nuevoEmail }
    fun onPasswordChanged(nuevoPassword: String) { _contrasenya.value = nuevoPassword }
    fun onPasswordAgainChanged(nuevoPasswordAgain: String) { _contrasenyaDos.value = nuevoPasswordAgain }

    // ---------- Enviar formulario ----------
    fun registrar() {
        val valido = validator()
        _error.value = Errores(_errorNombreCompleto.value, _errorNombreUsuario.value, _errorEmail.value, _errorContrasenya.value, _errorContrasenyaDos.value)
        if (valido) _state.value = SignUpState.Success
    }

    // ---------- Validación ----------
    fun validator() : Boolean {
        _errorNombreCompleto.value = if (checkNotBlank(_nombreCompleto.value.toString())) null else "El campo nombre es obligatorio"
        _errorNombreUsuario.value = if (checkNotBlank(_nombreUsuario.value.toString())) null else "El campo nombre del usuario es obligatorio"
        _errorEmail.value = checkEmail(_email.value.toString())?.takeIf { it.isNotBlank() }
        _errorContrasenya.value = checkPassword(_contrasenya.value.toString())?.takeIf { it.isNotBlank() }
        _errorContrasenyaDos.value = checkPasswordAgain(_contrasenya.value.toString(), _contrasenyaDos.value.toString())?.takeIf { it.isNotBlank() }

        return listOf(
            _errorNombreCompleto, _errorNombreUsuario, _errorEmail,
            _errorContrasenya, _errorContrasenyaDos
        ).all { it.value == null }
    }

    fun checkNotBlank(name : String) : Boolean {
        return name.isNotEmpty() && name.isNotBlank()
    }

    fun checkEmail(email : String) : String? {
        if (email.isEmpty() || email.isBlank()) return "El campo email es obligatorio"
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) return "El patrón del email es incorrecto. Ejemplo: aaaaa@gmail.com"
        return null
    }

    fun checkPassword(password : String) : String? {
        if (password.isEmpty() || password.isBlank()) return "El campo contraseña es obligatorio"
        else if (password.length < 8) return "La contraseña debe tener al menos 8 caracteres"
        else if (password.none() { it.isUpperCase() }) return "Debe contener al menos una mayúscula"
        else if (password.none() { it.isLowerCase() }) return "Debe contener al menos una minúscula"
        else if (password.none() { it.isDigit() }) return "Debe contener al menos un número"
        else if (password.none() { !it.isLetterOrDigit() }) return "Debe contener al menos un carácter especial"
        return null
    }

    fun checkPasswordAgain(password : String, passwordAgain : String) : String? {
        if (passwordAgain.isBlank()) return "El campo repetir contraseña es obligatorio"
        else if (passwordAgain != password) return "Las contraseñas no coinciden"
        return null
    }
}