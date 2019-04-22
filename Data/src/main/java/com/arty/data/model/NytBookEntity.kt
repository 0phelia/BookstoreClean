package com.arty.data.model

class NytBookEntity(
    val title: String,
    val description: String,
    val authors: String,
    val ranking: BookRankingEntity,
    val isbn10: String,
    val isbn13: String,
    val datePublished: String,
    val publisher: String

) {

}
