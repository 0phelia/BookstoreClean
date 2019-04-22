package com.arty.cache.mapper

import com.arty.cache.model.BookQueryResult
import com.arty.cache.model.CachedAuthor
import com.arty.data.model.BookAuthorEntity

class AuthorMapper : Mapper<BookQueryResult, List<BookAuthorEntity>, List<CachedAuthor>>{

    override fun mapQueryResultToEntity(type: BookQueryResult): List<BookAuthorEntity> {
        return type.authors.split(*arrayOf(","), ignoreCase = true).map { name ->
            BookAuthorEntity(name)
        }
    }

    override fun mapEntityToModelsBundle(type: List<BookAuthorEntity>): List<CachedAuthor> {
        return type.map { author -> CachedAuthor(author.id, author.name) }
    }

}
