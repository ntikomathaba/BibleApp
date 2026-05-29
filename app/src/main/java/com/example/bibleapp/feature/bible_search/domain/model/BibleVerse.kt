package com.example.bibleapp.feature.bible_search.domain.model

data class BibleVerse(
    val reference: String = "",
    val verses: List<Verse> = emptyList(),
    val text: String = "",
    val translationName: String = "",
    val translationNote: String = ""
)


