package com.example.bibleapp.feature.bible.domain.model

data class Verse(
    val bookName: String = "",
    val chapter: Int = 0,
    val verse: Int = 0,
    val text: String = ""
)