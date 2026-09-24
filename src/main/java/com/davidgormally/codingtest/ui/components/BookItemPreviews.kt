package com.davidgormally.codingtest.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.davidgormally.codingtest.constants.ApiConstants
import com.davidgormally.codingtest.domain.model.Book

@Preview(showBackground = true)
@Composable
fun BookItemPreviewDefault() {
    MaterialTheme {
        BookItem(
            book =
                Book(
                    key = "test-key",
                    title = "Effective Python Programming",
                    author = "Brett Slatkin",
                    coverUrl = "${ApiConstants.COVER_BASE_URL}12345${ApiConstants.COVER_SIZE_MEDIUM}",
                    firstPublishYear = 2019,
                    subjects = listOf("Python", "Programming", "Software Development"),
                    isbn = listOf("9780134853987"),
                    language = listOf("English"),
                    publishers = listOf("Addison-Wesley Professional"),
                    numberOfPages = 384,
                ),
            onBookClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookItemPreviewLongTitle() {
    MaterialTheme {
        BookItem(
            book =
                Book(
                    key = "test-key-2",
                    title = "Python Programming: A Complete Guide to Learning Python Programming Language with Practical Examples and Exercises",
                    author = "John Smith",
                    coverUrl = null,
                    firstPublishYear = 2020,
                    subjects = listOf("Python", "Programming"),
                    isbn = null,
                    language = null,
                    publishers = null,
                    numberOfPages = null,
                ),
            onBookClick = {},
        )
    }
}
