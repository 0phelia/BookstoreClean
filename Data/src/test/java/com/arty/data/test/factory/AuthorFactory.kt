package com.arty.data.test.factory

import com.arty.data.model.BookAuthorEntity
import com.arty.domain.model.BookAuthor

object AuthorFactory {

    fun makeAuthorList(count: Int): List<BookAuthor> {
        return mutableListOf<BookAuthor>().apply {
            repeat(count) {
                add(makeAuthor())
            }
        }
    }

    fun makeAuthorEntityList(count: Int): List<BookAuthorEntity> {
        return mutableListOf<BookAuthorEntity>().apply {
            repeat(count) {
                add(makeAuthorEntity())
            }
        }
    }

    fun makeAuthorEntity(): BookAuthorEntity {
        return BookAuthorEntity(DataFactory.randomString())
    }

    fun makeAuthor(): BookAuthor {
        return BookAuthor(DataFactory.randomString(), DataFactory.randomString())
    }

}