package com.arty.domain.interactor

import com.arty.domain.interactor.browse.GetBooks
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

class GetBooksTest {
    lateinit var getBooks: GetBooks
    @Mock lateinit var booksRepository: BooksRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBooks = GetBooks(booksRepository)
    }


    @Test
    fun getBooksReturns() {
        runBlocking {
            val fakeBooks = BookDataFactory.makeBookList(4)
            stubGetBooks(fakeBooks)

            val usecaseResult = getBooks.execute()
            assertEquals(fakeBooks, (usecaseResult as Result.Success).value)
        }
    }

    private fun stubGetBooks(books: List<Book>) {
        runBlocking {
            whenever(booksRepository.getBooks()).thenReturn(books)
        }
    }

}