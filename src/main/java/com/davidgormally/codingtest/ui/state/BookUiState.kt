package com.davidgormally.codingtest.ui.state

import androidx.compose.runtime.Immutable
import com.davidgormally.codingtest.domain.model.Book

@Immutable
data class BookUiState(
    val listState: BookState = BookState.Loading,
    val selectedBook: Book? = null,
)
