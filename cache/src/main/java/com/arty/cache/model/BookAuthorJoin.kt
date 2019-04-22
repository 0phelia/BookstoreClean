package com.arty.cache.model

import androidx.room.*

@Entity(
    tableName = "books_authors",
    indices = [Index(value = ["book_id"]), Index(value = ["author_id"])],
    foreignKeys =
        [ForeignKey(
            entity = CachedAuthor::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("author_id")
        ),
        ForeignKey(
            entity = CachedBook::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("book_id")
        )]
)
data class BookAuthorJoin(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "book_id")
    val bookId: String,
    @ColumnInfo(name = "author_id")
    val authorId: String
) {
    @Ignore
    constructor(bookId: String, authorId: String) : this(0, bookId, authorId)
}