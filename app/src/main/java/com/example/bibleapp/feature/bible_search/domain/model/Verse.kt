package com.example.bibleapp.feature.bible_search.domain.model

data class Verse(
    val bookName: String = "",
    val chapter: Int = 0,
    val verse: Int = 0,
    val text: String = ""
)