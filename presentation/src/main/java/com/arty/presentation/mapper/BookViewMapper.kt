package com.arty.presentation.mapper

import com.arty.domain.model.Book
import com.arty.presentation.model.BookView
import javax.inject.Inject

class BookViewMapper @Inject constructor(): Mapper<Book, BookView> {

    override fun mapToView(type: Book): BookView {
        return with(type) {
            BookView(id, title, description, authors.toString(), ranking.rank, coverImage, datePublished, publisher, isLiked)
        }
    }

}