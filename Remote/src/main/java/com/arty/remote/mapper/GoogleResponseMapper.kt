package com.arty.remote.mapper

import com.arty.data.model.GoogleBookEntity
import com.arty.remote.model.google.GoogleBooksResponseModel
import javax.inject.Inject

open class GoogleResponseMapper @Inject constructor() : ModelMapper<GoogleBooksResponseModel, List<GoogleBookEntity>?> {
    override fun mapFromModel(model: GoogleBooksResponseModel): List<GoogleBookEntity>? {
        if (model.items == null) {
            return null
        } else {
            return model.items.map { book ->
                with(book.volumeInfo) {
                    GoogleBookEntity(
                        title,
                        authors ?: emptyList(),
                        publishedDate ?: "",
                        description,
                        pageCount,
                        imageLinks.smallThumbnail,
                        imageLinks.thumbnail
                    )
                }
            }
        }
    }
}