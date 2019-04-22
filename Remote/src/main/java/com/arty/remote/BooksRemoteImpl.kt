package com.arty.remote

import com.arty.data.model.GoogleBookEntity
import com.arty.data.model.NytBookEntity
import com.arty.data.repository.BooksRemote
import com.arty.remote.mapper.GoogleResponseMapper
import com.arty.remote.mapper.NytBooksModelMapper
import com.arty.remote.model.ApiKeyBundle
import com.arty.remote.service.GoogleBooksService
import com.arty.remote.service.NYTimesBookListsService
import javax.inject.Inject

class BooksRemoteImpl @Inject constructor(
    private val nytimesService: NYTimesBookListsService,
    private val googleService: GoogleBooksService,
    private val nytModelMapper: NytBooksModelMapper,
    private val googleResponseMapper: GoogleResponseMapper,
    private val apiKeyBundle: ApiKeyBundle
): BooksRemote {

    override suspend fun getNYTimesBooks(listName: String): List<NytBookEntity> {
        val nytBooks = nytimesService
            .getBookListAsync(listName, apiKeyBundle.nytBookApiKey).await()
        return nytModelMapper.mapFromModel(nytBooks)
    }

    override suspend fun getGoogleBooks(isbn13: String): List<GoogleBookEntity>? {
        val query = isbnToQuery(isbn13)
        val googleBookDetails = googleService
            .getBookDetailsAsync(query, apiKeyBundle.googleBookApiKey).await()
        return googleResponseMapper.mapFromModel(googleBookDetails)
    }

    private fun isbnToQuery(isbn: String): String {
        return "isbn:$isbn"
    }

}