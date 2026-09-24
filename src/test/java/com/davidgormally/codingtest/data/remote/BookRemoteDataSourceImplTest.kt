package com.davidgormally.codingtest.data.remote

import com.davidgormally.codingtest.constants.ApiConstants
import com.davidgormally.codingtest.data.api.OpenLibraryApi
import com.davidgormally.codingtest.data.dto.BookDto
import com.davidgormally.codingtest.data.dto.BookSearchResponseDto
import com.davidgormally.codingtest.data.mapper.BookMapper
import com.davidgormally.codingtest.domain.model.Book
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookRemoteDataSourceImplTest {
    private val openLibraryApi: OpenLibraryApi = mockk()
    private val bookMapper: BookMapper = mockk()

    private lateinit var bookRemoteDataSourceImpl: BookRemoteDataSourceImpl

    @Before
    fun setUp() {
        bookRemoteDataSourceImpl = BookRemoteDataSourceImpl(openLibraryApi, bookMapper)
    }

    @Test
    fun `getPopularBooks should return mapped books from API`() = runTest {
        // Given
        val bookDto =
            BookDto(
                key = "test-key",
                title = "Test Book",
                authorNames = listOf("Test Author"),
                coverId = 12345,
                firstPublishYear = 2020,
                isbn = null,
                language = null,
                subjects = null,
                publishers = null,
                numberOfPages = null,
            )

        val apiResponse = BookSearchResponseDto(listOf(bookDto))
        val expectedBook =
            Book(
                key = "test-key",
                title = "Test Book",
                author = "Test Author",
                coverUrl = "${ApiConstants.COVER_BASE_URL}12345${ApiConstants.COVER_SIZE_MEDIUM}",
                firstPublishYear = 2020,
                subjects = null,
                isbn = null,
                language = null,
                publishers = null,
                numberOfPages = null,
            )

        coEvery { openLibraryApi.searchBooksByTitle("Python", 20) } returns apiResponse
        coEvery { bookMapper.mapToDomainList(listOf(bookDto)) } returns listOf(expectedBook)

        // When
        val result = bookRemoteDataSourceImpl.getPopularBooks()

        // Then
        assertEquals(listOf(expectedBook), result)
    }

    @Test
    fun `getPopularBooks should propagate API error`() = runTest {
        // Given
        val error = RuntimeException("API error")
        coEvery { openLibraryApi.searchBooksByTitle("Python", 20) } throws error

        // When & Then
        try {
            bookRemoteDataSourceImpl.getPopularBooks()
            org.junit.Assert.fail("Expected exception")
        } catch (e: RuntimeException) {
            assertEquals(error, e)
        }
    }

    @Test
    fun `getPopularBooks should return empty list when API returns empty docs`() = runTest {
        // Given
        val apiResponse = BookSearchResponseDto(emptyList())
        coEvery { openLibraryApi.searchBooksByTitle("Python", 20) } returns apiResponse
        coEvery { bookMapper.mapToDomainList(emptyList()) } returns emptyList()

        // When
        val result = bookRemoteDataSourceImpl.getPopularBooks()

        // Then
        assertEquals(emptyList<List<Book>>(), result)
    }
}
