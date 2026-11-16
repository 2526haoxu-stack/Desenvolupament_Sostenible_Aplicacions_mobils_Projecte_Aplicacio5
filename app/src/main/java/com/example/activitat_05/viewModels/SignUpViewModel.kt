package com.example.activitat_05.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class User(
    val nombre: String,
    val apellido: String,
    val email: String,
    val fechaNacimiento: String,
    val telefono: String,
    val contrasenya: String,
    val contrasenyaDos: String
)
class SignUpViewModel : ViewModel() {
    private val _user = MutableLiveData<User>(User("Hugo", "Blanco", "hugo_blanco@gmail.com", "01/01/2007",
        "+34 666 666 666", "Hugo123456789", "Hugo123456789"))
    val user : LiveData<User> = _user
}