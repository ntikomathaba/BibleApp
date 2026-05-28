package com.example.bibleapp.feature.books.domain.repo

import com.example.bibleapp.feature.books.domain.model.Books

interface BibleBookRepo {
    fun getBibleBooks(): Books
}