package com.example.bibleapp.feature.books.domain.repo

import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.books.domain.model.Books

interface BibleBookRepo {
    suspend fun getBibleBooks(): BaseResult<Books?, Error>
}