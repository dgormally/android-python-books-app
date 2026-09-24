package com.davidgormally.codingtest.domain.datasource

import com.davidgormally.codingtest.domain.model.Book

interface BookRemoteDataSource {
    suspend fun getPopularBooks(): List<Book>
}
