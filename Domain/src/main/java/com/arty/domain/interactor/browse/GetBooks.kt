package com.arty.domain.interactor.browse

import com.arty.domain.interactor.BaseUseCase
import com.arty.domain.model.Book
import com.arty.domain.repository.BooksRepository
import javax.inject.Inject

open class GetBooks  @Inject constructor(
    private val booksRepository: BooksRepository
): BaseUseCase<Nothing?, List<Book>>() {

    override var useCaseFunction: suspend (Nothing?) -> List<Book> = {
        booksRepository.getBooks()
    }

}

