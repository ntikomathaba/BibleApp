package com.example.bibleapp.feature.books.presentation

import com.example.bibleapp.feature.books.domain.model.BibleBook
import com.example.bibleapp.feature.books.domain.model.Chapters
import com.example.bibleapp.feature.books.domain.model.Verses

data class BibleBookUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val bibleBooks: List<BibleBook> = emptyList(),
    val chapters: Chapters? = Chapters(),
    val verses: Verses? = Verses(),

    val currentBookId: String = "",
    val currentChapter: Int = 0,
    val currentVerse: Int = 0
)