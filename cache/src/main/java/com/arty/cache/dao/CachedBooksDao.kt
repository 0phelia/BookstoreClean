package com.arty.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arty.cache.model.BookAuthorJoin
import com.arty.cache.model.BookQueryResult
import com.arty.cache.model.CachedAuthor
import com.arty.cache.model.CachedBook

@Dao
abstract class CachedBooksDao {

    companion object {
        const val QUERY_GET_BOOKS_BASIC = "SELECT books.id AS id, " +
                "books.title AS title, " +
                "books.description AS description, " +
                "GROUP_CONCAT(authors.name) AS authors, " +
                "books.rank AS rank, " +
                "books.rankLastWeek AS rankLastWeek, " +
                "books.weeksOnList AS weeksOnList, " +
                "books.coverImage AS coverImage, " +
                "books.pageCount AS pageCount, " +
                "books.datePublished AS datePublished, " +
                "books.publisher AS publisher, " +
                "books.is_liked AS is_liked " +
                "FROM books_authors " +
                "JOIN books ON books_authors.book_id = books.id " +
                "JOIN authors ON books_authors.author_id = authors.id " +
                "GROUP BY books.id "

        const val BOOK_TABLE_NAME = "books"
    }

    @Query(QUERY_GET_BOOKS_BASIC)
    abstract fun getBooks(): List<BookQueryResult>

    @Query( "$QUERY_GET_BOOKS_BASIC HAVING books.id = :bookId")
    abstract fun getBook(bookId: String): BookQueryResult

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertBooks(books: List<CachedBook>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAuthors(authors: List<CachedAuthor>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAuthorBookJoin(bookAuthorJoin: List<BookAuthorJoin>)

    @Query("DELETE FROM $BOOK_TABLE_NAME")
    abstract fun deleteBooks()

    @Query("$QUERY_GET_BOOKS_BASIC HAVING books.is_liked = 1")
    abstract fun getLikedBooks(): List<BookQueryResult>

    @Query("UPDATE $BOOK_TABLE_NAME SET is_liked = :isLiked WHERE id = :bookId")
    abstract fun setLikedBook(isLiked: Boolean, bookId: String)

}