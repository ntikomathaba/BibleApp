package com.example.bibleapp.feature.bible.presentation

import com.example.bibleapp.feature.bible.domain.model.BibleVerse

data class BibleUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val bibleVerse: BibleVerse = BibleVerse()
)