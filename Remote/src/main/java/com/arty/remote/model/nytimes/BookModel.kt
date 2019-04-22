package com.arty.remote.model.nytimes

import com.squareup.moshi.Json

data class BookModel(
    val id: String,
    @field:Json(name = "list_name") val listName: String,
    @field:Json(name = "display_name") val displayName: String,
    @field:Json(name = "bestsellers_date") val bestsellersDate: String,
    @field:Json(name = "published_date") val publishedDate: String,
    @field:Json(name = "rank") val rank: Int,
    @field:Json(name = "rank_last_week") val rankLastWeek: Int,
    @field:Json(name = "weeks_on_list") val weeksOnList: Int,
    @field:Json(name = "asterisk") val asterisk: Int,
    @field:Json(name = "dagger") val dagger: Int,
    @field:Json(name = "amazon_product_url") val amazonProductUrl: String,
    @field:Json(name = "isbns") val isbns: List<BookIsbnModel>,
    @field:Json(name = "book_details") val bookDetails: List<BookDetailsModel>,
    @field:Json(name = "reviews") val reviews: List<BookReviewModel>
)
