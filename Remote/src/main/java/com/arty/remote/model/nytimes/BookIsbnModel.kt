package com.arty.remote.model.nytimes

import com.squareup.moshi.Json

data class BookIsbnModel(
    @field:Json(name = "isbn10") val isbn10: String,
    @field:Json(name = "isbn13") val isbn13: String)

