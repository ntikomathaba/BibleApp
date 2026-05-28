package com.example.bibleapp.feature.books.presentation

sealed class BibleBookEvent{
    data object FetchBibleBooks: BibleBookEvent()
    data class OnClickBook(val bookId: String): BibleBookEvent()
    data class GetChapters(val bookId: String): BibleBookEvent()
    data class GetVerses(val bookId: String, val chapter: Int): BibleBookEvent()
}