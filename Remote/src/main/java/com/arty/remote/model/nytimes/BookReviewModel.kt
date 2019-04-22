package com.arty.remote.model.nytimes

import com.squareup.moshi.Json

data class BookReviewModel(
    @field:Json(name = "book_review_link") val bookReviewLink: String,
    @field:Json(name = "first_chapter_link") val firstChapterLink: String,
    @field:Json(name = "sunday_review_link") val sundayReviewLink: String,
    @field:Json(name = "article_chapter_link") val articleChapterLink: String
)

