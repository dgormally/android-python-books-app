package com.davidgormally.codingtest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidgormally.codingtest.domain.usecase.GetPopularBooksUseCase
import com.davidgormally.codingtest.ui.intent.BookIntent
import com.davidgormally.codingtest.ui.state.BookState
import com.davidgormally.codingtest.ui.state.BookUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val getPopularBooksUseCase: GetPopularBooksUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookUiState())
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()

    init {
        onIntent(BookIntent.LoadBooks)
    }

    fun onIntent(intent: BookIntent) {
        when (intent) {
            is BookIntent.LoadBooks -> {
                _uiState.update { it.copy(listState = BookState.Loading) }
                viewModelScope.launch {
                    try {
                        val books = getPopularBooksUseCase()
                        _uiState.update { it.copy(listState = BookState.Success(books)) }
                    } catch (_: Exception) {
                        _uiState.update { it.copy(listState = BookState.Error) }
                    }
                }
            }
            is BookIntent.SelectBook -> {
                _uiState.update { it.copy(selectedBook = intent.book) }
            }
            is BookIntent.ClearSelection -> {
                _uiState.update { it.copy(selectedBook = null) }
            }
        }
    }
}
