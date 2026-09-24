package com.davidgormally.codingtest.data.remote

import com.davidgormally.codingtest.data.api.OpenLibraryApi
import com.davidgormally.codingtest.data.mapper.BookMapper
import com.davidgormally.codingtest.domain.datasource.BookRemoteDataSource
import com.davidgormally.codingtest.domain.model.Book
import javax.inject.Inject

class BookRemoteDataSourceImpl @Inject constructor(
    private val api: OpenLibraryApi,
    private val bookMapper: BookMapper,
) : BookRemoteDataSource {
    override suspend fun getPopularBooks(): List<Book> {
        val response = api.searchBooksByTitle("Python", 20)
        return bookMapper.mapToDomainList(response.docs)
    }
}
