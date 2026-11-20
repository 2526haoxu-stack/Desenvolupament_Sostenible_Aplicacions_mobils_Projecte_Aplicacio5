package com.example.activitat_05.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.text.iterator

class LoginViewModel : ViewModel() {
    sealed class logEvents {
        data object Idle : logEvents()
        data object Success : logEvents()
    }
    data class Error(
        val userError: String?,
        val passError: String?
    ) : logEvents()

    // private val _errorUser = MutableLiveData<String?>()
    // private val _errorPass = MutableLiveData<String?>()
    private val _loginTrue = MutableLiveData<logEvents>(logEvents.Idle)
    // val errorUser: LiveData<String?> = _errorUser
    // val errorPass: LiveData<String?> = _errorPass
    val loginTrue: LiveData<logEvents> = _loginTrue

    fun login(user: String, pass: String) {
        val userOk = user.esUserValid()
        val passOk = pass.esPasswordValid()

        // _errorUser.value = if(userOk) null else "Usuario inválido"
        // _errorPass.value = if (passOk) null else "Contraseña inválida"

        if(userOk && passOk) _loginTrue.value = logEvents.Success
        else {
            _loginTrue.value = Error(
                userError = if(userOk) null else "Usuario inválido",
                passError = if (passOk) null else "Contraseña inválida"
            )
        }
    }

    fun resetEvent() {
        _loginTrue.value = logEvents.Idle
    }


    private fun String.esUserValid(): Boolean = trim().length > 2
    private fun String.esPasswordValid(): Boolean {
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