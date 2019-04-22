package com.arty.remote.model.nytimes

import com.squareup.moshi.Json

data class BookListModel(
    @field:Json(name = "status") val status: String,
    @field:Json(name = "num_results") val numResults: Int,
    @field:Json(name = "last_modified") val lastModified: String,
    @field:Json(name = "results") val results: List<BookModel>)