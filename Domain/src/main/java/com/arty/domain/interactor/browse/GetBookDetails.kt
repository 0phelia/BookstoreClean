package com.arty.domain.interactor.browse

import com.arty.domain.interactor.BaseUseCase
import com.arty.domain.model.Book
import com.arty.domain.repository.BooksRepository
import javax.inject.Inject

open class GetBookDetails @Inject constructor(
    private val booksRepository: BooksRepository
) : BaseUseCase<GetBookDetails.Params, Book>() {

    data class Params constructor(val bookId: String) {
        companion object {
            fun makeParams(bookId: String): Params {
                return Params(bookId)
            }
        }
    }

    override var useCaseFunction: suspend (GetBookDetails.Params?) -> Book = { params ->
        if (params == null) throw IllegalArgumentException("Can't be null")
        booksRepository.getBookDetails(params.bookId)
    }
}


