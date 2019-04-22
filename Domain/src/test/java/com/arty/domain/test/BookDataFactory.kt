package com.arty.domain.test

import com.arty.domain.model.Book
import com.arty.domain.model.BookAuthor
import com.arty.domain.model.BookRanking
import com.arty.domain.test.DataFactory.randomBoolean
import com.arty.domain.test.DataFactory.randomInt
import com.arty.domain.test.DataFactory.randomUuid

object BookDataFactory {

    fun makeBook(): Book {
        return Book(randomUuid(), randomUuid(), randomUuid(),
            makeAuthorList(2), makeRanking(),
            randomUuid(), randomInt(5), randomUuid(), randomUuid(), randomBoolean())
    }

    fun makeBookList(count: Int): List<Book> {
        return mutableListOf<Book>().apply {
            repeat(count) {
                add(makeBook())
            }
        }
    }

    fun makeAuthorList(count: Int): List<BookAuthor> {
        return mutableListOf<BookAuthor>().apply {
            repeat(count) {
                add(makeAuthor())
            }
        }
    }

    fun makeAuthor(): BookAuthor {
        return BookAuthor(randomUuid(), randomUuid())
    }

    fun makeRanking(): BookRanking {
        return BookRanking(randomInt(5), randomInt(100), randomInt(100))
    }
}