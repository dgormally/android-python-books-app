package com.davidgormally.codingtest.ui.intent

import com.davidgormally.codingtest.domain.model.Book

sealed interface BookIntent {
    data object LoadBooks : BookIntent

    data class SelectBook(val book: Book) : BookIntent

    data object ClearSelection : BookIntent
}
