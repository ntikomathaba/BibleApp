package com.example.bibleapp.feature.verse_of_the_day.data.mapper

import com.example.bibleapp.feature.books.data.local.FavouriteVerseEntity
import com.example.bibleapp.feature.verse_of_the_day.data.remote.dto.VerseOfTheDayDto
import com.example.bibleapp.feature.verse_of_the_day.domain.model.VerseOfTheDay

fun VerseOfTheDayDto.toDomain(): VerseOfTheDay {
    return VerseOfTheDay(
        bookId = bookId,
        bookName = book,
        chapterNum = chapter,
        verseNum = verse,
        text = text
    )
}

fun VerseOfTheDay.toEntity(): FavouriteVerseEntity{
    return FavouriteVerseEntity(
        bookId = bookId,
        chapter = chapterNum,
        verse = verseNum,
        text = text,
        book = bookName
    )
}