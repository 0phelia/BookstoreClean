package com.arty.data.mapper

import com.arty.data.model.BookAuthorEntity
import com.arty.data.model.BookEntity
import com.arty.data.model.GoogleBookEntity
import com.arty.data.model.NytBookEntity
import javax.inject.Inject

class GoogleAndNytBookMerger @Inject constructor() : EntityMerger<GoogleBookEntity?, NytBookEntity, BookEntity> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun merge(google: GoogleBookEntity?, nyt: NytBookEntity): BookEntity {
        return BookEntity(
            nyt.isbn13,
            nyt.title,
            nyt.description,
            splitAuthors(nyt.authors),
            nyt.ranking,
            google?.coverMedium,
            google?.pageCount ?: 0,
            nyt.datePublished,
            nyt.publisher
        )
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    private fun splitAuthors(authors: String): List<BookAuthorEntity> {
        return authors.split(",", "&", "and", ignoreCase = true).map { name ->
            BookAuthorEntity(name.hashCode().toString(), name)
        }
    }
}