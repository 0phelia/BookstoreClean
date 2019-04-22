package com.arty.domain.model


class Book (val id: String, val title: String, val description: String,
            val authors: List<BookAuthor>,
            val ranking: BookRanking,
            val coverImage: String?,
            val pageCount: Int,
            val datePublished: String,
            val publisher: String,
            val isLiked: Boolean)
