package com.example.bibleapp.feature.books.domain.use_case

import javax.inject.Inject

data class BibleUseCases @Inject constructor(
    val getBibleBooksUseCase: GetBibleBooksUseCase,
    val getChaptersUseCase: GetChaptersUseCase,
    val getVersesUseCase: GetVersesUseCase
)
