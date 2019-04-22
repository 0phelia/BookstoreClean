package com.arty.remote.mapper

import com.arty.data.model.BookRankingEntity
import com.arty.data.model.NytBookEntity
import com.arty.remote.model.nytimes.BookListModel
import com.arty.remote.model.nytimes.BookModel
import javax.inject.Inject

open class NytBooksModelMapper @Inject constructor() :
    ModelMapper<BookListModel, List<NytBookEntity>> {

    override fun mapFromModel(model: BookListModel): List<NytBookEntity> {
        return model.results.map { book ->
            with(book.bookDetails[0]) {
                NytBookEntity(title, description, author, mapRanking(book), primary_isbn10, primary_isbn13, book.publishedDate, publisher)
            }
        }
    }

    private fun mapRanking(book: BookModel): BookRankingEntity {
        return BookRankingEntity(book.rank, book.rankLastWeek, book.weeksOnList)
    }

}
