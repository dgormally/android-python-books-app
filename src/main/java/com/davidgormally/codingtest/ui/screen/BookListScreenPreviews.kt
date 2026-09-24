package com.davidgormally.codingtest.ui.screen

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.davidgormally.codingtest.constants.ApiConstants
import com.davidgormally.codingtest.domain.model.Book
import com.davidgormally.codingtest.ui.state.BookState
import com.davidgormally.codingtest.ui.state.BookUiState

@Preview(showBackground = true)
@Composable
fun BookListScreenPreviewLoading() {
    MaterialTheme {
        BookListScreenContent(
            state = BookUiState(listState = BookState.Loading),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookListScreenPreviewSuccess() {
    MaterialTheme {
        BookListScreenContent(
            state =
                BookUiState(
                    listState =
                        BookState.Success(
                            listOf(
                                Book(
                                    key = "test-key-1",
                                    title = "Effective Python Programming",
                                    author = "Brett Slatkin",
                                    coverUrl = "${ApiConstants.COVER_BASE_URL}12345${ApiConstants.COVER_SIZE_MEDIUM}",
                                    firstPublishYear = 2019,
                                    subjects = listOf("Python", "Programming"),
                                    isbn = listOf("9780134853987"),
                                    language = listOf("English"),
                                    publishers = listOf("Addison-Wesley Professional"),
                                    numberOfPages = 384,
                                ),
                                Book(
                                    key = "test-key-2",
                                    title = "Python Cookbook",
                                    author = "David Beazley",
                                    coverUrl = "${ApiConstants.COVER_BASE_URL}67890${ApiConstants.COVER_SIZE_MEDIUM}",
                                    firstPublishYear = 2013,
                                    subjects = listOf("Python", "Recipes"),
                                    isbn = listOf("9781449340377"),
                                    language = listOf("English"),
                                    publishers = listOf("O'Reilly Media"),
                                    numberOfPages = 706,
                                ),
                            ),
                        ),
                ),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookListScreenPreviewEmpty() {
    MaterialTheme {
        BookListScreenContent(
            state = BookUiState(listState = BookState.Success(emptyList())),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookListScreenPreviewError() {
    MaterialTheme {
        BookListScreenContent(
            state = BookUiState(listState = BookState.Error),
            onIntent = {},
        )
    }
}
