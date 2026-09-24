package com.davidgormally.codingtest.ui.state

import com.davidgormally.codingtest.domain.model.Book

sealed class BookState {
    data object Loading : BookState()

    data class Success(val books: List<Book>) : BookState()

    data object Error : BookState()
}
