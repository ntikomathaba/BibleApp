package com.example.bibleapp.feature.books.domain.model

data class BibleBook(
    val id: String,
    val name: String,
    val url: String,
    val testament: Testament
)

enum class Testament{
    OLD, NEW, NONE
}