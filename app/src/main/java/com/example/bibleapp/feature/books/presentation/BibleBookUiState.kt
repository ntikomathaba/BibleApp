package com.example.bibleapp.feature.books.presentation

import com.example.bibleapp.feature.books.domain.model.Books
import com.example.bibleapp.feature.books.domain.model.Chapters
import com.example.bibleapp.feature.books.domain.model.Verses

data class BibleBookUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val bibleBooks: Books? = Books(),
    val chapters: Chapters? = Chapters(),
    val verses: Verses? = Verses(),

    val bookId: String = "",
    val chapter: Int = 0,
    val currentVerse: Int = 0
)