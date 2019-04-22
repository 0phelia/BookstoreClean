package com.arty.presentation.browse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.arty.domain.interactor.Result
import com.arty.domain.interactor.browse.GetBooks
import com.arty.domain.model.Book
import com.arty.presentation.mapper.BookViewMapper
import com.arty.presentation.model.BookView
import com.arty.presentation.state.Resource
import com.arty.presentation.state.ResourceState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class BrowseBooksViewModel @Inject constructor(
    private val getBooks: GetBooks,
    private val mapper: BookViewMapper
) : ViewModel() {

    val liveData: MutableLiveData<Resource<List<BookView>>> = MutableLiveData()
    val isLoading: LiveData<Boolean> = Transformations.map(liveData) { books ->
        books.status == ResourceState.LOADING
    }
    val isError: LiveData<Boolean> = Transformations.map(liveData) { books ->
        books.status == ResourceState.ERROR || (books.status == ResourceState.SUCCESS && books.data.isNullOrEmpty())
    }

    private var getBooksJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        getBooksJob?.cancel()
    }

    fun fetchBooks() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))

        getBooksJob?.cancel()
        getBooksJob = GlobalScope.launch {
            val fetchedBooks = getBooks.execute()
            when (fetchedBooks) {
                is Result.Success -> handleSuccess(fetchedBooks)
                is Result.Error -> handleError(fetchedBooks)
            }
        }
    }

    private fun handleSuccess(fetchedBooks: Result.Success<List<Book>>) {
        liveData.postValue(
            Resource(ResourceState.SUCCESS, fetchedBooks.value.sortedBy { book -> book.ranking.rank }.map { mapper.mapToView(it) }, null)
        )
    }

    private fun handleError(fetchedBooks: Result.Error) {
        liveData.postValue(
            Resource(ResourceState.ERROR, null, fetchedBooks.message)
        )
    }
}