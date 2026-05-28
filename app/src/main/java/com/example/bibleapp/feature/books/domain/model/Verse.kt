package com.example.bibleapp.feature.books.domain.model

data class Verse(
    val id: String,
    val book: String,
    val chapter: Int,
    val verse: Int,
    val text: String
)