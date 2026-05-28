package com.example.bibleapp.feature.books.data.remote.dto

data class BookChaptersResponseDto(
    val translation: TranslationDto,
    val chapters: List<ChapterDto>
)