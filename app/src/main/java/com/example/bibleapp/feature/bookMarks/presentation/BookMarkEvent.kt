package com.example.bibleapp.feature.bookMarks.presentation

import com.example.bibleapp.feature.books.domain.model.Verse

sealed class BookMarkEvent {
    data class OnDeleteBookMark(val bookMark: Verse): BookMarkEvent()
}