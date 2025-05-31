package org.course.moviesapp.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.course.moviesapp.screens.home.Item
import org.course.moviesapp.screens.home.imageList

class DetailViewModel(private val itemId:Int): ViewModel() {
    var state by mutableStateOf<Item?>(null)
        private set

    init {
        state = imageList.find {
            it.id == itemId
        }
    }
}