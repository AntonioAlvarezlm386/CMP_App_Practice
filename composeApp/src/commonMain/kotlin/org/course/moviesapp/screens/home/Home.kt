package org.course.moviesapp.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import moviesapp.composeapp.generated.resources.Res
import moviesapp.composeapp.generated.resources.clone
import moviesapp.composeapp.generated.resources.delete
import moviesapp.composeapp.generated.resources.more_options
import org.jetbrains.compose.resources.stringResource

@Composable
fun Home(viewModel:HomeviewModel = viewModel()){
   HomeGrid(
       viewModel.state,
       //Como la estructura de la funcion es igual a la lambda
       //onActionClick = { action, index -> viewModel.onAction(action, index) }
       onActionClick = viewModel::onAction
   )

}

@Composable
fun MoreActionsIconButton(onActionClick:(Action) -> Unit) {
    var optionsOpen: Boolean by remember{ mutableStateOf(false) }

    IconButton(onClick = { optionsOpen = !optionsOpen }){
        Icon(Icons.Default.MoreVert, contentDescription = stringResource(Res.string.more_options))

        DropdownMenu(
            expanded = optionsOpen,
            onDismissRequest = { optionsOpen = false }
        ){
            DropdownMenuItem(
                text = { Text(stringResource(Res.string.clone)) },
                onClick = { optionsOpen = false; onActionClick(Action.CLONE)}
            )

            DropdownMenuItem (
                text = { Text(stringResource(Res.string.delete)) },
                onClick = { optionsOpen = false; onActionClick(Action.DELETE) }
            )
        }
    }
}
