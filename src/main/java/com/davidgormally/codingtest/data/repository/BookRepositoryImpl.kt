package com.davidgormally.codingtest.data.repository

import com.davidgormally.codingtest.di.IoDispatcher
import com.davidgormally.codingtest.domain.datasource.BookRemoteDataSource
import com.davidgormally.codingtest.domain.model.Book
import com.davidgormally.codingtest.domain.repository.BookRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val remoteDataSource: BookRemoteDataSource,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : BookRepository {
    override suspend fun getPopularBooks(): List<Book> {
        return withContext(ioDispatcher) {
            remoteDataSource.getPopularBooks()
        }
    }
}
