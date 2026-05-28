package com.example.bibleapp.feature.books.data.mapper

import com.example.bibleapp.feature.books.data.remote.dto.BibleBookDto
import com.example.bibleapp.feature.books.domain.model.BibleBook
import com.example.bibleapp.feature.books.domain.model.Books

fun List<BibleBookDto>.toDomain(): Books {
    return Books(
        books = this.map { BibleBook(name = it.name, url =  it.url, id =  it.id) }
    )
}