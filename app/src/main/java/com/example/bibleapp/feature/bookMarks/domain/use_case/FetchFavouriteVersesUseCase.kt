package com.example.bibleapp.feature.bookMarks.domain.use_case

import com.example.bibleapp.feature.books.data.local.FavouriteVerseDao
import javax.inject.Inject

class FetchFavouriteVersesUseCase @Inject constructor(
    private val favouriteVerseDao: FavouriteVerseDao
) {
    operator fun invoke() = favouriteVerseDao.getAlVerses()
}