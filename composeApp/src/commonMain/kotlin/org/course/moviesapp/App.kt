package org.course.moviesapp


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview


import org.course.moviesapp.screens.home.Home

@Composable
@Preview
fun App() {
    MaterialTheme {
        Home()
    }
}