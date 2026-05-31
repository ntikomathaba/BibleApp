package com.example.bibleapp.feature.bible_search.presentation

import com.example.bibleapp.feature.bible_search.domain.model.BibleVerse

data class BibleSearchUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = "",
    val bibleVerse: BibleVerse? = null
)