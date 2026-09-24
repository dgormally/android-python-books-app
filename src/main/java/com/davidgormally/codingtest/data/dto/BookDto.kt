package com.davidgormally.codingtest.data.dto

import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("key")
    val key: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("author_name")
    val authorNames: List<String>? = null,
    @SerializedName("cover_i")
    val coverId: Int? = null,
    @SerializedName("first_publish_year")
    val firstPublishYear: Int? = null,
    @SerializedName("isbn")
    val isbn: List<String>? = null,
    @SerializedName("language")
    val language: List<String>? = null,
    @SerializedName("subject")
    val subjects: List<String>? = null,
    @SerializedName("publisher")
    val publishers: List<String>? = null,
    @SerializedName("number_of_pages_median")
    val numberOfPages: Int? = null,
)

data class BookSearchResponseDto(
    @SerializedName("docs")
    val docs: List<BookDto>,
)
