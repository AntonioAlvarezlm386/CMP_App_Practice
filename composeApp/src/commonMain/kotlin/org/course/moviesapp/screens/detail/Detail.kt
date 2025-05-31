package org.course.moviesapp.screens.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
data class Detail(val id:Int)

@Composable
fun Detail(viewModel: () -> DetailViewModel){
    viewModel.sta ?.let{ item ->
        Text(item.title)
    }
}