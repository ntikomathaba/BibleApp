package com.example.bibleapp.feature.books.domain.repo

import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.books.domain.model.Books
import com.example.bibleapp.feature.books.domain.model.Chapters
import com.example.bibleapp.feature.books.domain.model.Verses

interface BibleBookRepo {
    suspend fun getBibleBooks(): BaseResult<Books?, Error>
    suspend fun getBookChapters(bookId: String): BaseResult<Chapters, Error>
    suspend fun getVerses(bookId: String, chapter: Int): BaseResult<Verses, Error>
}