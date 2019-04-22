package com.arty.domain.interactor

import com.arty.domain.interactor.browse.GetBookDetails
import com.arty.domain.model.Book
import com.arty.domain.repository.BooksRepository
import com.arty.domain.test.BookDataFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class GetBookDetailsTest {
    lateinit var getBookDetails: GetBookDetails
    @Mock
    lateinit var booksRepository: BooksRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBookDetails = GetBookDetails(booksRepository)
    }


    @Test
    fun getBookDetailsReturns() {
        runBlocking {
            val fakeBook = BookDataFactory.makeBook()
            stubGetBookDetails(fakeBook, fakeBook.id)

            val usecaseResult = getBookDetails.execute(GetBookDetails.Params.makeParams(fakeBook.id))
            assertEquals(fakeBook, (usecaseResult as Result.Success).value)
        }
    }

    @Test
    fun getBookDetailsThrows() {
        runBlocking {
            val usecaseResult = getBookDetails.execute(null)
            assert(usecaseResult is Result.Error)
        }
    }


    private fun stubGetBookDetails(book: Book, bookId: String) {
        runBlocking {
            whenever(booksRepository.getBookDetails(bookId)).thenReturn(book)
        }
    }

}