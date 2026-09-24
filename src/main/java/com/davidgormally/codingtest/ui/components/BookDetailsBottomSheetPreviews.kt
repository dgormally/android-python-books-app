package com.davidgormally.codingtest.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.davidgormally.codingtest.constants.ApiConstants
import com.davidgormally.codingtest.domain.model.Book

@Preview(showBackground = true)
@Composable
fun BookDetailsBottomSheetPreviewDefault() {
    MaterialTheme {
        BookDetailsBottomSheet(
            book =
                Book(
                    key = "/works/OL12345W",
                    title = "Effective Python Programming",
                    author = "Brett Slatkin",
                    coverUrl = "${ApiConstants.COVER_BASE_URL}12345${ApiConstants.COVER_SIZE_MEDIUM}",
                    firstPublishYear = 2019,
                    subjects = listOf("Python", "Programming", "Software Development", "Best Practices", "Code Quality"),
                    isbn = listOf("9780134853987", "9780134853988"),
                    language = listOf("English"),
                    publishers = listOf("Addison-Wesley Professional"),
                    numberOfPages = 384,
                ),
            onDismiss = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookDetailsBottomSheetPreviewMinimal() {
    MaterialTheme {
        BookDetailsBottomSheet(
            book =
                Book(
                    key = "/works/OL67890W",
                    title = "Python Basics",
                    author = "Jane Doe",
                    coverUrl = null,
                    firstPublishYear = null,
                    subjects = listOf("Python"),
                    isbn = null,
                    language = null,
                    publishers = null,
                    numberOfPages = null,
                ),
            onDismiss = {},
        )
    }
}
