package com.example.bibleapp.feature.bookMarks.domain.mapper

import com.example.bibleapp.feature.books.data.local.FavouriteVerseEntity
import com.example.bibleapp.feature.books.data.mapper.toDomain
import com.example.bibleapp.feature.books.domain.model.Verse

fun List<FavouriteVerseEntity>.toDomain(): List<Verse> {
    return this.map {
        it.toDomain()
    }
}