package org.course.moviesapp.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.Serializable
import moviesapp.composeapp.generated.resources.Res
import moviesapp.composeapp.generated.resources.app_name
import moviesapp.composeapp.generated.resources.clone
import moviesapp.composeapp.generated.resources.delete
import moviesapp.composeapp.generated.resources.grid_view
import moviesapp.composeapp.generated.resources.item_cloned
import moviesapp.composeapp.generated.resources.item_deleted
import moviesapp.composeapp.generated.resources.list_view
import moviesapp.composeapp.generated.resources.more_options
import org.jetbrains.compose.resources.stringResource

@Serializable
object Home


//cuenado el viewModel no reibe ningun argumento de sebe pasar de esta forma. Para iOS
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    onItemClick: (Item) -> Unit,
    viewModel:HomeviewModel = viewModel{ HomeviewModel() }
){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var isGridFocused:Boolean by remember { mutableStateOf(false) }
    var snackbarHostState  = remember { SnackbarHostState() }


    //nos permite trabajar con la notificacion y se realiza implicitamente la vaidacion de que no sea nula
    viewModel.state.notification?.let{ notification ->
        val stringRes = if(notification.action == Action.CLONE) Res.string.item_cloned else Res.string.item_deleted
        val message = stringResource(stringRes, notification.title)

        //para evistar sideEffects cada ves que se recomponga la interfaz se debe implementar LaunhedEffect
        //El parametro define cuando se lanzara, funciona cuando camba el mensaje si el mensaje fuera ifual que el anterior no funcionaria
        LaunchedEffect(message) {
            //la funcion showSnackbar debe ejecutarse en un contexto de corutinas, por que la funcion se termina de ejecutar hasta que se desaparece el snackbar
            snackbarHostState.showSnackbar(message)
            //limpiar la notificacion
            viewModel.clearNotification()
        }
    }

    Scaffold (
        topBar = {
            HomeTopAppBar(
                isGridFocused,
                { isGridFocused = !isGridFocused },
                scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ){ padding ->
        if(isGridFocused){
            HomeGrid(
                viewModel.state.items,
                onItemclick = onItemClick,
                //Como la estructura de la funcion es igual a la lambda
                //onActionClick = { action, index -> viewModel.onAction(action, index) }
                onActionClick = viewModel::onAction,
                modifier = Modifier.padding(padding)
            )
        }else{
            HomeList(
                viewModel.state.items,
                onItemClick = onItemClick,
                onActionClick = viewModel::onAction,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(isGridFocused:Boolean, onGridclick: () -> Unit, scrollBehavior: TopAppBarScrollBehavior){
    TopAppBar(
        title = {
            Text(stringResource(Res.string.app_name))
        },
        scrollBehavior = scrollBehavior,
        actions = {
            IconButton (onClick = { onGridclick() }){
                Icon(
                    imageVector = if(isGridFocused) Icons.AutoMirrored.Default.ViewList  else  Icons.Default.GridView,
                    contentDescription = if(isGridFocused) stringResource(Res.string.list_view) else stringResource(Res.string.grid_view)
                )
            }
        }
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
