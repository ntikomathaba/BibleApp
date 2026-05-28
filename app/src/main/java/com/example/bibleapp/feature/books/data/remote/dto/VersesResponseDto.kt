package com.example.bibleapp.feature.books.data.remote.dto

data class VersesResponseDto(
    val translation: TranslationDto,
    val verses: List<VerseDto>
)