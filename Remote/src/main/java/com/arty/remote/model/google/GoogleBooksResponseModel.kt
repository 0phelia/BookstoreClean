package com.arty.remote.model.google

import com.squareup.moshi.Json

data class GoogleBooksResponseModel(
    @field:Json(name = "kind") val kind: String,
    @field:Json(name = "totalItems") val totalItems: Int,
    @field:Json(name = "items") val items: List<GoogleBookModel>?)

