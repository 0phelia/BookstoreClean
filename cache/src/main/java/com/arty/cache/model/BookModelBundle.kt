package com.arty.cache.model

data class BookModelBundle (
    val authors: List<CachedAuthor>,
    val book: CachedBook,
    val bookAuthorJoin: List<BookAuthorJoin>
)