package com.davidgormally.codingtest.ui.viewmodel

import app.cash.turbine.test
import com.davidgormally.codingtest.domain.model.Book
import com.davidgormally.codingtest.domain.usecase.GetPopularBooksUseCase
import com.davidgormally.codingtest.ui.intent.BookIntent
import com.davidgormally.codingtest.ui.state.BookState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private val getPopularBooksUseCase: GetPopularBooksUseCase = mockk()

    private val sampleBook =
        Book(
            key = "test-key",
            title = "Test Book",
            author = "Test Author",
            coverUrl = null,
            firstPublishYear = 2020,
            subjects = null,
            isbn = null,
            language = null,
            publishers = null,
            numberOfPages = null,
        )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `LoadBooks intent emits Success when use case returns books`() =
        runTest(testDispatcher) {
            val books = listOf(sampleBook)
            coEvery { getPopularBooksUseCase() } returns books

            val viewModel = BookViewModel(getPopularBooksUseCase)
            advanceUntilIdle()

            val listState = viewModel.uiState.value.listState
            assertTrue(listState is BookState.Success)
            assertEquals(books, (listState as BookState.Success).books)
        }

    @Test
    fun `LoadBooks intent emits Error when use case throws`() =
        runTest(testDispatcher) {
            coEvery { getPopularBooksUseCase() } throws RuntimeException("network")

            val viewModel = BookViewModel(getPopularBooksUseCase)
            advanceUntilIdle()

            assertTrue(viewModel.uiState.value.listState is BookState.Error)
        }

    @Test
    fun `SelectBook then ClearSelection update selectedBook`() =
        runTest(testDispatcher) {
            coEvery { getPopularBooksUseCase() } returns listOf(sampleBook)

            val viewModel = BookViewModel(getPopularBooksUseCase)
            advanceUntilIdle()

            viewModel.uiState.test {
                val loaded = awaitItem()
                assertTrue(loaded.listState is BookState.Success)
                assertNull(loaded.selectedBook)

                viewModel.onIntent(BookIntent.SelectBook(sampleBook))
                assertEquals(sampleBook, awaitItem().selectedBook)

                viewModel.onIntent(BookIntent.ClearSelection)
                assertNull(awaitItem().selectedBook)

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `Retry LoadBooks recovers from Error to Success`() =
        runTest(testDispatcher) {
            coEvery { getPopularBooksUseCase() } throws RuntimeException("network") andThen listOf(sampleBook)

            val viewModel = BookViewModel(getPopularBooksUseCase)
            advanceUntilIdle()
            assertTrue(viewModel.uiState.value.listState is BookState.Error)

            viewModel.onIntent(BookIntent.LoadBooks)
            advanceUntilIdle()

            val listState = viewModel.uiState.value.listState
            assertTrue(listState is BookState.Success)
            assertEquals(listOf(sampleBook), (listState as BookState.Success).books)
        }
}
