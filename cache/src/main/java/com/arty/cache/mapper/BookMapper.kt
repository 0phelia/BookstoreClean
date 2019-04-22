package com.arty.cache.mapper

import com.arty.cache.model.BookAuthorJoin
import com.arty.cache.model.BookModelBundle
import com.arty.cache.model.BookQueryResult
import com.arty.cache.model.CachedBook
import com.arty.data.model.BookEntity
import javax.inject.Inject

class BookMapper  @Inject constructor(
    private val authorMapper: AuthorMapper,
    private val rankingMapper: RankingMapper
): Mapper<BookQueryResult, BookEntity, BookModelBundle> {

    override fun mapQueryResultToEntity(type: BookQueryResult): BookEntity {
        val authors = authorMapper.mapQueryResultToEntity(type)
        val ranking = rankingMapper.mapQueryResultToEntity(type)

        return with(type) {
            BookEntity(id, title, description, authors, ranking,
                coverImage, pageCount, datePublished, publisher, is_liked)
        }
    }

    override fun mapEntityToModelsBundle(type: BookEntity): BookModelBundle {
        val authors = authorMapper.mapEntityToModelsBundle(type.authors)
        val ranking = rankingMapper.mapEntityToModelsBundle(type.ranking)
        val joins = authors.map { author -> BookAuthorJoin(bookId = type.id, authorId = author.id) }

        val cachedBook = with(type) {
            CachedBook(id, title, description, ranking, coverImage ?: "", pageCount, datePublished, publisher, isLiked)
        }
        return BookModelBundle(authors, cachedBook, joins)
    }

}