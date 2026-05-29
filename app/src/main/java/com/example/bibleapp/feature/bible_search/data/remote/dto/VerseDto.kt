package com.example.bibleapp.feature.bible_search.data.remote.dto

import com.google.gson.annotations.SerializedName

data class VerseDto(
    val bookId: String,
    @SerializedName("book_name")
    val bookName: String,
    val chapter: Int,
    val verse: Int,
    val text: String
)