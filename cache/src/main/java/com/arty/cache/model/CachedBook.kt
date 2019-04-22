package com.arty.cache.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class CachedBook(
    @PrimaryKey var id: String,
    var title: String,
    var description: String,
    @Embedded var ranking: CachedRanking,
    var coverImage: String,
    var pageCount: Int,
    var datePublished: String,
    val publisher: String,
    @ColumnInfo(name = "is_liked")
    var isLiked: Boolean
)
