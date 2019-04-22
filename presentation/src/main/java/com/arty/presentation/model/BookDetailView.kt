package com.arty.presentation.model

data class BookDetailView(
    val id: String, val title: String, val description: String,
    val authors: String,
    val rank: Int,
    val coverImage: String?,
    val datePublished: String,
    val publisher: String,
    val pages: Int,
    val isLiked: Boolean
)