package com.arty.remote.model.google

import com.squareup.moshi.Json

data class GoogleBooksVolumeInfoModel(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "authors") val authors: List<String>?,
    @field:Json(name = "publishedDate") val publishedDate: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "pageCount") val pageCount: Int,
    @field:Json(name = "imageLinks") val imageLinks: GoogleBookImageLinksModel)