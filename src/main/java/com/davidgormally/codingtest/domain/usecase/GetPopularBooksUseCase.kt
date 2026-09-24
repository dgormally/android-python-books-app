package com.davidgormally.codingtest.domain.usecase

import com.davidgormally.codingtest.domain.model.Book
import com.davidgormally.codingtest.domain.repository.BookRepository
import javax.inject.Inject

class GetPopularBooksUseCase @Inject constructor(
    private val repository: BookRepository,
) {
    suspend operator fun invoke(): List<Book> =
        repository.getPopularBooks()
}
