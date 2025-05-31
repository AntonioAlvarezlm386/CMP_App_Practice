package org.course.moviesapp


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.course.moviesapp.screens.detail.Detail
import org.course.moviesapp.screens.detail.DetailViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview


import org.course.moviesapp.screens.home.Home
import org.course.moviesapp.screens.login.Login

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = Login){
            composable<Login>{
                Login( onLoggedIn = { navController.navigate( Home ) } )
            }
            composable<Home> {
                Home(onItemClick = {
                    navController.navigate(
                        Detail(it.id)
                    )
                })
            }
            composable<Detail> { backstackEntry ->
                val detail = backstackEntry.toRoute<Detail>()

                Detail(viewModel = { DetailViewModel(detail.id) })
            }
        }
    }
}