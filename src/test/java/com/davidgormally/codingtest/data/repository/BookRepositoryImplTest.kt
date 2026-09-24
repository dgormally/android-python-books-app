package com.davidgormally.codingtest.data.repository

import com.davidgormally.codingtest.domain.datasource.BookRemoteDataSource
import com.davidgormally.codingtest.domain.model.Book
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookRepositoryImplTest {
    private val bookRemoteDataSource: BookRemoteDataSource = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var bookRepositoryImpl: BookRepositoryImpl

    @Before
    fun setUp() {
        bookRepositoryImpl = BookRepositoryImpl(bookRemoteDataSource, testDispatcher)
    }

    @Test
    fun `getPopularBooks should return books from remote data source`() = runTest {
        // Given
        val expectedBooks =
            listOf(
                Book(
                    key = "test-key-1",
                    title = "Test Book 1",
                    author = "Test Author 1",
                    coverUrl = "https://test.com/cover1.jpg",
                    firstPublishYear = 2020,
                    subjects = listOf("programming"),
                    isbn = null,
                    language = null,
                    publishers = null,
                    numberOfPages = null,
                ),
            )

        coEvery { bookRemoteDataSource.getPopularBooks() } returns expectedBooks

        // When
        val result = bookRepositoryImpl.getPopularBooks()

        // Then
        assertEquals(expectedBooks, result)
    }

    @Test
    fun `getPopularBooks should propagate error from remote data source`() = runTest {
        // Given
        val error = RuntimeException("Network error")
        coEvery { bookRemoteDataSource.getPopularBooks() } throws error

        // When & Then
        try {
            bookRepositoryImpl.getPopularBooks()
            org.junit.Assert.fail("Expected exception")
        } catch (e: Throwable) {
            val unwrapped = e.cause ?: e
            assertEquals("Network error", unwrapped.message)
            org.junit.Assert.assertTrue(unwrapped is RuntimeException)
        }
    }

    @Test
    fun `getPopularBooks should return empty list when remote data source returns empty list`() = runTest {
        // Given
        coEvery { bookRemoteDataSource.getPopularBooks() } returns emptyList()

        // When
        val result = bookRepositoryImpl.getPopularBooks()

        // Then
        assertEquals(emptyList<List<Book>>(), result)
    }

    @Test
    fun `getPopularBooks should call remote data source`() = runTest {
        // Given
        val expectedBooks =
            listOf(
                Book(
                    key = "test-key",
                    title = "Test Book",
                    author = "Test Author",
                    coverUrl = null,
                    firstPublishYear = null,
                    subjects = null,
                    isbn = null,
                    language = null,
                    publishers = null,
                    numberOfPages = null,
                ),
            )

        coEvery { bookRemoteDataSource.getPopularBooks() } returns expectedBooks

        // When
        val result = bookRepositoryImpl.getPopularBooks()

        // Then
        assertEquals(expectedBooks, result)
    }
}
