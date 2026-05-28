package com.example.bibleapp.feature.books.presentation

sealed class BibleBookEvent{
    data object FetchBibleBooks: BibleBookEvent()
}