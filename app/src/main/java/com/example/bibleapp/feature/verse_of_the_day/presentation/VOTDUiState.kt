package com.example.bibleapp.feature.verse_of_the_day.presentation

import com.example.bibleapp.feature.verse_of_the_day.domain.model.VerseOfTheDay

data class VOTDUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val verse: VerseOfTheDay = VerseOfTheDay(),
)