package com.example.bibleapp.feature.verse_of_the_day.data.remote.dto

import com.example.bibleapp.feature.books.data.remote.dto.TranslationDto
import com.google.gson.annotations.SerializedName

data class VerseOfTheDayResponseDto(
    val translation: TranslationDto,
    @SerializedName("random_verse")
    val verseOfTheDay: VerseOfTheDayDto
)

data class VerseOfTheDayDto(
    @SerializedName("book_id")
    val bookId: String,
    val book: String,
    val chapter: Int,
    val verse: Int,
    val text: String
)