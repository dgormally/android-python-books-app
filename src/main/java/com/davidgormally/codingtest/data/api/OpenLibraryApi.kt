package com.davidgormally.codingtest.data.api

import com.davidgormally.codingtest.data.dto.BookSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryApi {
    @GET("search.json")
    suspend fun searchBooksByTitle(
        @Query("title") title: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("fields") fields: String =
            "key,title,author_name,cover_i,first_publish_year,isbn,language,subject,publisher,number_of_pages_median",
    ): BookSearchResponseDto
}
