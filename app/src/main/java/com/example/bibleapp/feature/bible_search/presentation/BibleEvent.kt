package com.example.bibleapp.feature.bible_search.presentation

sealed class BibleEvent {
    data class GetBibleVerse(val query: String): BibleEvent()
}