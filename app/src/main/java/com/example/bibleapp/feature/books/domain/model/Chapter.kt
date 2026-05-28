package com.example.bibleapp.feature.books.domain.model

data class Chapter(
    val id: String,
    val book: String,
    val chapter: Int,
    val url: String
)