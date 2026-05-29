package com.example.bibleapp.feature.books.domain.repo

import com.example.bibleapp.feature.books.domain.model.Verse
import kotlinx.coroutines.flow.Flow

interface FavouriteVerseRepo {
    suspend fun add(verse: Verse)
    suspend fun remove(verseId: String)
    suspend fun toggle(verse: Verse)
    fun getAllVerses(): Flow<List<Verse>>
}