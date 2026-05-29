package com.example.bibleapp.feature.bookMarks.domain.use_case

import javax.inject.Inject

data class FavouriteVerseUseCases @Inject constructor(
    val favVerseUse: FavVerseUseCase,
    val unFavVerseUseCase: UnFavVerseUseCase,
    val fetchFavouriteVersesUseCase: FetchFavouriteVersesUseCase
)