package org.course.moviesapp.screens.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


@Composable
fun HomeList(items:List<Item>, onActionClick:(Action, Int) -> Unit, modifier: Modifier = Modifier){
    LazyColumn (
        modifier = modifier
            .fillMaxWidth(),
        //Para evitar que el contenido se corte en el appbar, y pase por debajo
        contentPadding = WindowInsets.statusBars.asPaddingValues()
    ){
        itemsIndexed(items, key = { _, it -> it.id }) { index, image ->
            /*  Esta es una opcion otra mas eficiente es la de ListItem
            Row (
                modifier = Modifier
                    .padding(16.dp)
            ) {
                //Esta clase nos la proporciona la libreria de coil
                AsyncImage(
                    model = image.thumb,
                    contentDescription = image.title,
                    modifier = Modifier.size(48.dp)
                        .clip(shape = CircleShape)
                )
                Column {
                    Text(image.title)
                    Text(image.subtitle)
                }
            }*/

            //if(index != 0){
            //    HorizontalDivider()
            //}

            ListItem(
                headlineContent = { Text(image.title) },
                leadingContent = {
                    AsyncImage(
                        model = image.thumb,
                        contentDescription = image.title,
                        modifier = Modifier.size(48.dp)
                            .clip(shape = CircleShape)
                    )
                },
                supportingContent = {
                    Text(image.subtitle)
                },
                trailingContent = {
                    MoreActionsIconButton(){onActionClick(it, index)}
                },
                modifier = Modifier.animateItem()
            )
        }
    }
}