package com.arty.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
data class CachedAuthor(
    @PrimaryKey
    var id: String,
    var name: String
)