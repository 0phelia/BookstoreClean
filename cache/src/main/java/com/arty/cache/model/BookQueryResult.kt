package com.arty.cache.model

import androidx.room.ColumnInfo

data class BookQueryResult(
    var id: String,
    var title: String,
    var description: String,
    var authors: String,
    var rank: Int,
    var rankLastWeek: Int,
    var weeksOnList: Int,
    var coverImage: String,
    var pageCount: Int,
    var datePublished: String,
    val publisher: String,
    @ColumnInfo(name = "is_liked")
    var is_liked: Boolean
)
