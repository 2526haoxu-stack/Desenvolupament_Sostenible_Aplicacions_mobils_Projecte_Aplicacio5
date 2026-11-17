package com.example.activitat_05.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
class SignUpViewModel : ViewModel() {
    private val _nombre = MutableLiveData("")
    private val _apellido = MutableLiveData("")
    private val _email = MutableLiveData("")
    private val _fechaNacimiento = MutableLiveData("")
    private val _telefono = MutableLiveData("")
    private val _contrasenya = MutableLiveData("")
    private val _contrasenyaDos = MutableLiveData("")
    private val _errorNombre = MutableLiveData<String?>()
    private val _errorApellido = MutableLiveData<String?>()
    private val _errorEmail = MutableLiveData<String?>()
    private val _errorFechaNacimiento = MutableLiveData<String?>()
    private val _errorTelefono = MutableLiveData<String?>()
    private val _errorContrasenya = MutableLiveData<String?>()
    private val _errorContrasenyaDos = MutableLiveData<String?>()

    val nombre: LiveData<String> = _nombre
    val apellido: LiveData<String> = _apellido
    val email: LiveData<String> = _email
    val fechaNacimiento: LiveData<String> = _fechaNacimiento
    val telefono: LiveData<String> = _telefono
    val contrasenya: LiveData<String> = _contrasenya
    val contrasenyaDos: LiveData<String> = _contrasenyaDos
    val errorNombre: LiveData<String?> = _errorNombre
    val errorApellido: LiveData<String?> = _errorApellido
    val errorEmail: LiveData<String?> = _errorEmail
    val errorFechaNacimiento: LiveData<String?> = _errorFechaNacimiento
    val errorTelefono: LiveData<String?> = _errorTelefono
    val errorContrasenya: LiveData<String?> = _errorContrasenya
    val errorContrasenyaDos: LiveData<String?> = _errorContrasenyaDos

    fun onNombreChanged(nuevoNombre: String) { _nombre.value = nuevoNombre }
    fun onApellidoChanged(nuevoApellido: String) { _apellido.value = nuevoApellido }
    fun onEmailChanged(nuevoEmail: String) { _email.value = nuevoEmail }
    fun onBirthdayChanged(nuevoBirthday: String) { _fechaNacimiento.value = nuevoBirthday }
    fun onPhoneNumberChanged(nuevoPhoneNumber: String) { _telefono.value = nuevoPhoneNumber }
    fun onPasswordChanged(nuevoPassword: String) { _contrasenya.value = nuevoPassword }
    fun onPasswordAgainChanged(nuevoPasswordAgain: String) { _contrasenyaDos.value = nuevoPasswordAgain }

    fun validator() : Boolean {
        _errorNombre.value = if (_nombre.value.isNullOrBlank()) "El nombre es obligatorio" else null
        _errorApellido.value = if (_apellido.value.isNullOrBlank()) "El apellido es obligatorio" else null
        _errorEmail.value = when {
            _email.value.isNullOrBlank() -> "El email es obligatorio"
            !Patterns.EMAIL_ADDRESS.matcher(_email.value.toString()).matches() -> "El patrón del email es incorrecto. Ejemplo: aaaaa@gmail.com"
            else -> null
        }
        _errorContrasenya.value = when {
            _contrasenya.value.isNullOrBlank() -> "La contraseña es obligatoria"
            (_contrasenya.value?.length ?: 0) < 8 -> "La contraseña debe tener al menos 8 caracteres"
            _contrasenya.value?.none { it.isUpperCase() } == true -> "Debe contener al menos una mayúscula"
            _contrasenya.value?.none { it.isDigit() } == true -> "Debe contener al menos un número"
            _contrasenya.value?.none { it.isLetter() } == true -> "Debe contener al menos una letra"
            _contrasenya.value?.none { !it.isLetterOrDigit() } == true -> "Debe contener al menos un carácter especial"
            else -> null
        }
        _errorContrasenyaDos.value = when {
            _contrasenyaDos.value.isNullOrBlank() -> "La contraseña es obligatoria"
            !_contrasenyaDos.value.equals(_contrasenya.value) -> "Las contraseñas no coinciden"
            else -> null
        }

        return listOf(
            _errorNombre, _errorApellido, _errorEmail, _errorFechaNacimiento,
            _errorTelefono, _errorContrasenya, _errorContrasenyaDos
        ).all { it.value == null }
    }
}