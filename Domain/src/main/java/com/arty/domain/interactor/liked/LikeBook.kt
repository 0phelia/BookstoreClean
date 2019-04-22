package com.arty.domain.interactor.liked

import com.arty.domain.interactor.Result
import com.arty.domain.repository.BooksRepository
import javax.inject.Inject

open class LikeBook @Inject constructor(
    private val booksRepository: BooksRepository
) {

    data class Params constructor(val bookId: String, val like: Boolean) {
        companion object {
            fun makeParams(bookId: String, like: Boolean): Params {
                return Params(bookId, like)
            }
        }
    }

    suspend fun execute(params: Params): Result<Nothing?> {
        return try {
            when (params.like) {
                true -> booksRepository.likeBook(params.bookId)
                false -> booksRepository.unlikeBook(params.bookId)
            }
            Result.Success(null)
        } catch (e: Exception) {
            Result.Error(e.message, e)
        }
    }

}