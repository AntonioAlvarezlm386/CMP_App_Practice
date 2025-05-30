package org.course.moviesapp.screens.login


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import moviesapp.composeapp.generated.resources.Res
import moviesapp.composeapp.generated.resources.invalidEmail
import moviesapp.composeapp.generated.resources.invalidPassword
import org.jetbrains.compose.resources.StringResource

class LoginViewModel:ViewModel() {
    //Ya no esnecesario usar remember ya que estos no se encuentran dentro de un composable, por tanto sobreviven a las recomposiciones
    var state by mutableStateOf(UiState())
        private set

    data class UiState(
        val loggedIn: Boolean = false,
        val error:StringResource? = null
    )

    fun login(email:String, password:String){
        state = when{
            !email.contains("@") -> UiState(error = Res.string.invalidEmail)
            password.length < 5 -> UiState(error = Res.string.invalidPassword)
            else -> UiState(loggedIn = true)
        }
    }
}