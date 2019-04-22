package com.arty.remote.model.nytimes

import com.squareup.moshi.Json

data class BookDetailsModel(
    val id: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "contributor") val contributor: String,
    @field:Json(name = "author") val author: String,
    @field:Json(name = "contributor_note") val contributor_note: String,
    @field:Json(name = "publisher") val publisher: String,
    @field:Json(name = "primary_isbn13") val primary_isbn13: String,
    @field:Json(name = "primary_isbn10") val primary_isbn10: String)


