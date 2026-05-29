package com.example.bibleapp.feature.books.data.mapper

import com.example.bibleapp.feature.books.data.local.FavouriteVerseEntity
import com.example.bibleapp.feature.books.domain.model.Verse

fun Verse.toEntity(): FavouriteVerseEntity {
    return FavouriteVerseEntity(
        bookId = id,
        book = book,
        chapter = chapter,
        verse = verse,
        text = text,
        created = System.currentTimeMillis()
    )
}

fun FavouriteVerseEntity.toDomain(): Verse {
    return Verse(
        id = bookId,
        book = book,
        chapter = chapter,
        verse = verse,
        text = text
    )
}