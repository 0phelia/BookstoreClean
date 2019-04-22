package com.arty.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arty.domain.interactor.Result
import com.arty.domain.interactor.browse.GetBookDetails
import com.arty.domain.interactor.liked.LikeBook
import com.arty.domain.model.Book
import com.arty.presentation.mapper.BookDetailViewMapper
import com.arty.presentation.model.BookDetailView
import com.arty.presentation.state.Resource
import com.arty.presentation.state.ResourceState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getBookDetails: GetBookDetails,
    private val likeBook: LikeBook,
    private val mapper: BookDetailViewMapper
) : ViewModel() {

    val liveData: MutableLiveData<Resource<BookDetailView>> = MutableLiveData()

    private var getBookDetailJob: Job? = null
    private var likeBookJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        getBookDetailJob?.cancel()
        likeBookJob?.cancel()
    }

    /**
     *   GET BOOK
     */
    fun getBook(bookId: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))

        getBookDetailJob?.cancel()
        getBookDetailJob = GlobalScope.launch {
            val params = GetBookDetails.Params.makeParams(bookId)
            val bookDetail = getBookDetails.execute(params)
            when (bookDetail) {
                is Result.Success -> handleSuccess(bookDetail)
                is Result.Error -> handleError(bookDetail)
            }
        }
    }

    private fun handleSuccess(book: Result.Success<Book>) {
        liveData.postValue(
            Resource(ResourceState.SUCCESS, mapper.mapToView(book.value) , null)
        )
    }

    private fun handleError(fetchedBooks: Result.Error) {
        liveData.postValue(
            Resource(ResourceState.ERROR, null, fetchedBooks.message)
        )
    }

    /**
     *    LIKE BOOK
     */
    fun likeBook(bookId: String, like: Boolean) {
        likeBookJob?.cancel()
        likeBookJob = GlobalScope.launch {
            likeBook.execute(LikeBook.Params.makeParams(bookId, like))
        }
    }

    /**
     *    ADD TO CART
     */
    fun addToCart() {
        // out of scope
    }

}