package org.course.moviesapp.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeviewModel : ViewModel() {
    var state by mutableStateOf( imageList )
        private set

    private var nextKey = state.size

    fun onAction(action:Action, index:Int){
        state = state.toMutableList().apply {
            when(action){
                Action.CLONE -> add(index, get(index).copy(id = nextKey++))
                Action.DELETE -> removeAt(index)
            }
        }
    }
}


enum class Action{CLONE, DELETE}