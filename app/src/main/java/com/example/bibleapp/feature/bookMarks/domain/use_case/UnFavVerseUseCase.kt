package com.example.bibleapp.feature.bookMarks.domain.use_case

import com.example.bibleapp.feature.books.data.local.FavouriteVerseDao
import com.example.bibleapp.feature.books.data.local.FavouriteVerseEntity
import com.example.bibleapp.feature.books.domain.model.Verse
import timber.log.Timber
import javax.inject.Inject

class UnFavVerseUseCase @Inject constructor(
    private val favouriteVerseDao: FavouriteVerseDao
){
    suspend operator fun invoke(foundVerse: Verse?) {
        val verseEntity = FavouriteVerseEntity(
            bookId = foundVerse?.id ?: "",
            book = foundVerse?.book ?: "",
            chapter = foundVerse?.chapter ?: 0,
            text = foundVerse?.text ?: "",
            verse = foundVerse?.verse ?: 0
        )
        Timber.e("Deleting verse $foundVerse")
        favouriteVerseDao.deleteVerse(bookId = verseEntity.bookId, chapter = verseEntity.chapter, verse = verseEntity.verse)
    }
}