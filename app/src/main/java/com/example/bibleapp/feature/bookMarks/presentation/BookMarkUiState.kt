package com.example.bibleapp.feature.bookMarks.presentation

import com.example.bibleapp.feature.books.domain.model.Verse

data class BookMarkUiState(
    val isLoading: Boolean = false,
    val bookMarks: List<Verse> = emptyList()
)
