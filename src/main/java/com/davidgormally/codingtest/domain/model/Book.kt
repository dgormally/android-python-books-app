package com.davidgormally.codingtest.domain.model

data class Book(
    val key: String,
    val title: String,
    val author: String,
    val coverUrl: String?,
    val firstPublishYear: Int?,
    val subjects: List<String>?,
    val isbn: List<String>?,
    val language: List<String>?,
    val publishers: List<String>?,
    val numberOfPages: Int?,
)
