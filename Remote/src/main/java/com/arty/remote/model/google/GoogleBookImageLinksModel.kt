package com.arty.remote.model.google

import com.squareup.moshi.Json

data class GoogleBookImageLinksModel(
    @field:Json(name = "smallThumbnail") val smallThumbnail: String,
    @field:Json(name = "thumbnail") val thumbnail: String)
