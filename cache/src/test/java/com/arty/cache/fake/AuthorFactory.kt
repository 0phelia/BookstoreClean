package com.arty.cache.fake

import com.arty.data.model.BookAuthorEntity

object AuthorFactory {


    fun makeAuthorEntityList(count: Int): List<BookAuthorEntity> {
        return mutableListOf<BookAuthorEntity>().apply {
            repeat(count) {
                add(makeAuthorEntity())
            }
        }
    }

    fun makeAuthorEntity(): BookAuthorEntity {
        return BookAuthorEntity(DataFactory.randomUuid())
    }


}