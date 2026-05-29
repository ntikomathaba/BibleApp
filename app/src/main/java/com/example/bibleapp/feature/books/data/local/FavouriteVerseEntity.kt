package com.example.bibleapp.feature.books.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favourite_verses",
    indices = [Index(value = ["bookId", "chapter", "verse"], unique = true)]
)
data class FavouriteVerseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bookId: String,
    val book: String,
    val chapter: Int,
    val verse: Int,
    val text: String,
    val created: Long = System.currentTimeMillis()
)
