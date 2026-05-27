package com.example.bibleapp.feature.bible.presentation

sealed class BibleEvent {
    data class GetBibleVerse(val query: String): BibleEvent()
}