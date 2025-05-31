package org.course.moviesapp.screens.login
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.Serializable
import moviesapp.composeapp.generated.resources.Res
import moviesapp.composeapp.generated.resources.successLogin
import org.jetbrains.compose.resources.stringResource


@Serializable
object Login


@Composable
fun Login(
    onLoggedIn: () -> Unit,
    viewModel: LoginViewModel = viewModel{ LoginViewModel() }
){
    val state = viewModel.state
    val message = when{
        state.loggedIn -> stringResource(Res.string.successLogin)
        state.error != null -> stringResource(state.error)
        else -> null
    }

    LaunchedEffect(viewModel.state.loggedIn){
        if(viewModel.state.loggedIn) onLoggedIn()
    }

    var user : String by remember{ mutableStateOf("") }
    var password : String by remember{ mutableStateOf("") }

    val loginEnabled = password.isNotEmpty() && user.isNotEmpty()

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ){
        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("Email") },
            isError = state.error != null,
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        EditPasswordTextfield(
            password,
            {password = it},
            onDone = { if(loginEnabled) viewModel.login(user, password) },
            isError = state.error != null
        )
        Button(
            onClick = {
                viewModel.login(user, password )
            },
            enabled = loginEnabled
        ){
            Text("Login")
        }
        if(message != null){
            Text(text = message)
        }
    }
}


@Composable
//En la lista de argumentos, todos los valores opcionales deben ir al ultimo. El primer valor por defecto debe ser el modifier
fun EditPasswordTextfield(
        password:String,
        onChangePassword: (String) -> Unit,
        onDone: () -> Unit,
        isError:Boolean = false
){
    var isPasswordVisible by remember{ mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = { onChangePassword(it) },
        label = { Text("Password") },
        isError = isError,
        //transformar el contenido del campo
        visualTransformation = if(isPasswordVisible){
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible }
            ){
                Icon(
                    imageVector = if(isPasswordVisible){
                        Icons.Default.Visibility
                    }else{
                        Icons.Default.VisibilityOff
                    },
                    contentDescription = "icon"
                )
            }
        },
        singleLine = true,
        maxLines = 1,
        keyboardActions = KeyboardActions { onDone() }
    )
}