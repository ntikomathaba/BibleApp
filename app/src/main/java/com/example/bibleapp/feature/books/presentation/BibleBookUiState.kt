package com.example.bibleapp.feature.books.presentation

import com.example.bibleapp.feature.books.domain.model.Books

data class BibleBookUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val bibleBooks: Books = Books()
)