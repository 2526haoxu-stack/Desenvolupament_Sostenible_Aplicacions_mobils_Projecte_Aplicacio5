package com.example.activitat_05.loginViewModel
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.text.iterator

class LoginViewModel : ViewModel {
    private val _errorEmail = MutableLiveData<String?>()
    private val _errorPass = MutableLiveData<String?>()
    private val _loginTrue = MutableLiveData(false)

    val errorEmail: LiveData<String?> = _errorEmail
    val errorPass: LiveData<String?> = _errorPass
    val loginTrue: LiveData<Boolean> = _loginTrue

    fun login(email: String, pass: String) {
        val emailOk = email.esEmailValid()
        val passOk = pass.esPasswordValid()

        _errorEmail.value = if(emailOk) null else "Correo inválido"
        _errorPass.value = if (passOk) null else "COntraseña inválida"
    }




    fun String.esEmailValid(): Boolean = isNotBlank() &&
            Patterns.EMAIL_ADDRESS.matcher(this).matches()
    fun String.esPasswordValid(): Boolean {
        var espCar = 0
        var maj = 0
        var min = 0

        for (c in this) {
            if(!c.isLetterOrDigit()) espCar += 1
            else {
                if(c.isUpperCase()) maj += 1
                else min += 1
            }

        }
        return espCar > 0 && maj > 0 && min > 0 && this.length > 6
    }
}