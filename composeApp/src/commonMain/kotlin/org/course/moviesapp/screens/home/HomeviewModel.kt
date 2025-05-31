package org.course.moviesapp.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeviewModel : ViewModel() {
    var state by mutableStateOf( UiState(imageList) )
        private set

    data class UiState(
        val items:List<Item> = emptyList(),
        val notification: Notification? = null
    )

    data class Notification(
        val action: Action,
        val title:String
    )

    private var nextKey = state.items.size

    fun onAction(action:Action, index:Int){
        val newItems = state.items.toMutableList()
        val item = newItems[index]

        state = when(action){
            Action.CLONE -> {
                UiState(
                    items = newItems.apply { add(index, item.copy(id= nextKey++)) },
                    notification = Notification(action = action, item.title )
                )
            }
            Action.DELETE -> {
                UiState(
                    items = newItems.apply { removeAt(index) },
                    notification = Notification(action, item.title)
                )
            }

        }
    }

    fun clearNotification() {
        state = state.copy(notification = null)
    }
}


enum class Action{CLONE, DELETE}