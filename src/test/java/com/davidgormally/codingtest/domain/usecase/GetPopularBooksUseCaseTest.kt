package com.davidgormally.codingtest.domain.usecase

import com.davidgormally.codingtest.domain.model.Book
import com.davidgormally.codingtest.domain.repository.BookRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetPopularBooksUseCaseTest {
    private val bookRepository: BookRepository = mockk()
    private lateinit var getPopularBooksUseCase: GetPopularBooksUseCase

    @Before
    fun setUp() {
        getPopularBooksUseCase = GetPopularBooksUseCase(bookRepository)
    }

    @Test
    fun `invoke should return books from repository`() = runTest {
        // Given
        val expectedBooks =
            listOf(
                Book(
                    key = "test-key-1",
                    title = "Test Book 1",
                    author = "Test Author 1",
                    coverUrl = "https://test.com/cover1.jpg",
                    firstPublishYear = 2020,
                    subjects = listOf("programming", "android"),
                    isbn = listOf("123456789"),
                    language = listOf("en"),
                    publishers = listOf("Test Publisher"),
                    numberOfPages = 300,
                ),
                Book(
                    key = "test-key-2",
                    title = "Test Book 2",
                    author = "Test Author 2",
                    coverUrl = "https://test.com/cover2.jpg",
                    firstPublishYear = 2021,
                    subjects = listOf("kotlin", "development"),
                    isbn = listOf("987654321"),
                    language = listOf("en"),
                    publishers = listOf("Another Publisher"),
                    numberOfPages = 250,
                ),
            )

        coEvery { bookRepository.getPopularBooks() } returns expectedBooks

        // When
        val result = getPopularBooksUseCase()

        // Then
        assertEquals(expectedBooks, result)
    }

    @Test
    fun `invoke should propagate repository error`() = runTest {
        // Given
        val error = RuntimeException("Repository error")
        coEvery { bookRepository.getPopularBooks() } throws error

        // When & Then
        try {
            getPopularBooksUseCase()
            org.junit.Assert.fail("Expected exception")
        } catch (e: RuntimeException) {
            assertEquals(error, e)
        }
    }

    @Test
    fun `invoke should return empty list when repository returns empty list`() = runTest {
        // Given
        coEvery { bookRepository.getPopularBooks() } returns emptyList()

        // When
        val result = getPopularBooksUseCase()

        // Then
        assertEquals(emptyList<List<Book>>(), result)
    }
}
