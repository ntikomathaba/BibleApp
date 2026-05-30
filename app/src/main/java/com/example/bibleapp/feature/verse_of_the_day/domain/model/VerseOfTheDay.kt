package com.example.bibleapp.feature.verse_of_the_day.domain.model

data class VerseOfTheDay(
    val bookId: String = "",
    val bookName: String = "",
    val chapterNum: Int = 0,
    val verseNum: Int = 0,
    val text: String = ""
)
