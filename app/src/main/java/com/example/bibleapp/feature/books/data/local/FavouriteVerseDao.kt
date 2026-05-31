package com.example.bibleapp.feature.books.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteVerseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun favVerse(entity: FavouriteVerseEntity): Long

    @Query("DELETE FROM favourite_verses WHERE bookId = :id")
    suspend fun unfaveVerse(id: String)

    @Query("SELECT * FROM favourite_verses ORDER BY created DESC")
    fun getAlVerses(): Flow<List<FavouriteVerseEntity>>


    @Query("""
    DELETE FROM favourite_verses
    WHERE bookId = :bookId
    AND chapter = :chapter
    AND verse = :verse
""")
    suspend fun deleteVerse(
        bookId: String,
        chapter: Int,
        verse: Int
    )

    @Delete
    suspend fun delete(entity: FavouriteVerseEntity)
}
