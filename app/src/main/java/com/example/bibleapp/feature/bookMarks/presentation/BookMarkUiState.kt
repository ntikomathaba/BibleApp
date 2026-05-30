package com.example.bibleapp.feature.bookMarks.presentation

import com.example.bibleapp.feature.books.domain.model.Verse
import com.example.bibleapp.feature.books.domain.model.Verses

data class BookMarkUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val bookMarks: List<Verse> = emptyList(),
    val verses: Verses? = Verses(),
    val bookId: String = "",
    val chapter: Int = 0
)
