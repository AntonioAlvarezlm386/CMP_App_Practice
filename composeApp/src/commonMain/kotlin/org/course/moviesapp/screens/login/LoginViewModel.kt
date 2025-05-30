package org.course.moviesapp.screens.login


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
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
            !email.contains("@") -> UiState(error = "Invalid email")
            password.length < 5 -> UiState(error = "Invalid password")
            else -> UiState(loggedIn = true)
        }
    }
}