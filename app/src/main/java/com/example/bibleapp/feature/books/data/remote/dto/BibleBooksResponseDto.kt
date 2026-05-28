package com.example.bibleapp.feature.books.data.remote.dto

data class BibleBooksResponseDto(
    val translation: TranslationDto,
    val books: List<BibleBookDto>
)