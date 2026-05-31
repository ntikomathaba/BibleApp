package com.example.bibleapp.feature.bible_search.presentation

sealed class BibleSearchEvent {
    data class OnSearchQueryChanged(val query: String): BibleSearchEvent()
}