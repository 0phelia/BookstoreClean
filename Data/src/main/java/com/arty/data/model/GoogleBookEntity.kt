package com.arty.data.model

class GoogleBookEntity(
    val title: String,
    val authors: List<String>,
    val publishedDate: String,
    val description: String?,
    val pageCount: Int,
    val coverSmall: String,
    val coverMedium: String
) {

}
