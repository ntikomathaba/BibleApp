package com.example.bibleapp.feature.books.presentation.chapters

import com.example.bibleapp.feature.books.domain.model.BibleBook
import com.example.bibleapp.feature.books.domain.model.Books
import com.example.bibleapp.feature.books.domain.model.Chapter
import com.example.bibleapp.feature.books.domain.model.Chapters
import com.example.bibleapp.feature.books.domain.model.Verse
import com.example.bibleapp.feature.books.domain.model.Verses
import com.example.bibleapp.feature.books.presentation.BibleBookUiState

val mockBibleBookUiState = BibleBookUiState(
    isLoading = false,
    isError = false,
    bibleBooks = Books(
        listOf(
            BibleBook("GEN", "Genesis", "www.bible-api.com/GEN"),
            BibleBook("NUM", "Numbers", "www.bible-api.com/JHN"),
            BibleBook("JHN", "John", "www.bible-api.com/JHN"),
            BibleBook("MRK", "Mark", "www.bible-api.com/MRK"),
            BibleBook("LUK", "Luke", "www.bible-api.com/LUK"),
            BibleBook("MAT", "Matthew", "www.bible-api.com/MAT"),
        )
    ),
    chapters = Chapters(
        listOf(
            Chapter("GEN", "Genesis", 1, "www.bible-api.com/GEN/1"),
            Chapter("JHN", "John", 2, "www.bible-api.com/JHN/2"),
            Chapter("NUM", "Numbers", 3, "www.bible-api.com/NUM/3"),
            Chapter("MRK", "Mark", 4, "www.bible-api.com/MRK/4"),
            Chapter("LUK", "Luke", 5, "www.bible-api.com/LUK/5"),
            Chapter("MAT", "Matthew", 6, "www.bible-api.com/MAT/6"),
        )
    ),
    verses = Verses(
        listOf(
            Verse("GEN", "Genesis", 1, 1, "In the beginning"),
            Verse("JHN", "John", 2, 2, "There was light"),
            Verse("NUM", "Numbers", 3, 3, "And it was done"),
        )
    ),
    currentBookId = "GEN",
    currentChapter = 1
)