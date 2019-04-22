package com.arty.presentation.mapper

import com.arty.domain.model.Book
import com.arty.domain.model.BookAuthor
import com.arty.presentation.model.BookDetailView
import javax.inject.Inject

class BookDetailViewMapper @Inject constructor() : Mapper<Book, BookDetailView> {
    override fun mapToView(type: Book): BookDetailView {
        return with(type) {
            BookDetailView(
                id,
                title,
                description,
                mapAuthors(authors),
                ranking.rank,
                coverImage,
                datePublished,
                publisher,
                pageCount,
                isLiked
            )
        }
    }

    private fun mapAuthors(authors: List<BookAuthor>): String {
        return authors.joinToString { next ->
            next.name
        }
    }

}
