package com.arty.remote.model.google

import com.squareup.moshi.Json

data class GoogleBookModel(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "volumeInfo") val volumeInfo: GoogleBooksVolumeInfoModel)