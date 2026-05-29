package com.example.bibleapp.feature.books.data.repo

import com.example.bibleapp.feature.books.data.local.FavouriteVerseDao
import com.example.bibleapp.feature.books.data.mapper.toDomain
import com.example.bibleapp.feature.books.data.mapper.toEntity
import com.example.bibleapp.feature.books.domain.model.Verse
import com.example.bibleapp.feature.books.domain.repo.FavouriteVerseRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteVerseRepoImpl @Inject constructor(
    private val dao: FavouriteVerseDao
) : FavouriteVerseRepo {
    override suspend fun add(verse: Verse) {
        dao.favVerse(verse.toEntity())
    }

    override suspend fun remove(verseId: String) {
        dao.unfaveVerse(verseId)
    }

    override suspend fun toggle(verse: Verse) {

    }

    override fun getAllVerses(): Flow<List<Verse>> =
        dao.getAlVerses().map { entities ->
            entities.map { favVerse ->
                favVerse.toDomain()
            }
        }
}