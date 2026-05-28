package com.example.bibleapp.feature.books.data.repo

import com.example.bibleapp.feature.books.data.local.BibleBooksDataSource
import com.example.bibleapp.feature.books.domain.model.Books
import com.example.bibleapp.feature.books.domain.repo.BibleBookRepo
import javax.inject.Inject

class BibleBookRepoImpl @Inject constructor(
    val bibleBooksDataSource: BibleBooksDataSource,
): BibleBookRepo {
    override fun getBibleBooks(): Books {
        return bibleBooksDataSource.getBibleBooksFromAsset()
    }
}