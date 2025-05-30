package org.course.moviesapp.screens.home

data class Item(
    val id:Int,
    val title:String,
    val subtitle:String,
    val thumb:String
)


val imageList = (1 ..1000).map{
    Item(
        id = it,
        title = "title $it",
        subtitle = "subtitle $it",
        thumb = "https://picsum.photos/id/$it/200/200"
    )
}
