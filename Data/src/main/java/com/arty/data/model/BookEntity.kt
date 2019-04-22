package com.arty.data.model

data class BookEntity(val id: String, val title: String, val description: String,
                      val authors: List<BookAuthorEntity>,
                      val ranking: BookRankingEntity,
                      val coverImage: String?,
                      val pageCount: Int,
                      val datePublished: String, val publisher: String,
                      val isLiked: Boolean = false
)