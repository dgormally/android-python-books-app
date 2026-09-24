package com.davidgormally.codingtest.domain.repository

import com.davidgormally.codingtest.domain.model.Book

interface BookRepository {
    suspend fun getPopularBooks(): List<Book>
}
