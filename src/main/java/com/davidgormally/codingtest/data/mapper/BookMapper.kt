package com.davidgormally.codingtest.data.mapper

import com.davidgormally.codingtest.constants.ApiConstants
import com.davidgormally.codingtest.data.dto.BookDto
import com.davidgormally.codingtest.domain.model.Book

object BookMapper {
    private fun mapToDomain(dto: BookDto): Book {
        return Book(
            key = dto.key,
            title = dto.title,
            author = dto.authorNames?.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: "Unknown Author",
            coverUrl = dto.coverId?.let { "${ApiConstants.COVER_BASE_URL}$it${ApiConstants.COVER_SIZE_MEDIUM}" },
            firstPublishYear = dto.firstPublishYear,
            subjects = dto.subjects,
            isbn = dto.isbn,
            language = dto.language,
            publishers = dto.publishers,
            numberOfPages = dto.numberOfPages,
        )
    }

    fun mapToDomainList(bookDto: List<BookDto>): List<Book> {
        return bookDto.map { mapToDomain(it) }
    }
}
