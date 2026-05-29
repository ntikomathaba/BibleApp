package com.example.bibleapp.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bibleapp.feature.books.data.local.FavouriteVerseDao
import com.example.bibleapp.feature.books.data.local.FavouriteVerseEntity

@Database(
    entities = [FavouriteVerseEntity::class],
    version = 1,
    exportSchema = true
)
abstract class BibleDatabase : RoomDatabase() {
    abstract fun favouriteVerseDao(): FavouriteVerseDao

    companion object {
        const val DATABASE_NAME = "bible_db"
    }
}
