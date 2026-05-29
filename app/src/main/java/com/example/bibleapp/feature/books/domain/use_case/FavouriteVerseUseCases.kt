package com.example.bibleapp.feature.books.domain.use_case

import javax.inject.Inject

data class FavouriteVerseUseCases @Inject constructor(
    val favVerseUse: FavVerseUseCase
)