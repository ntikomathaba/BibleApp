package com.example.bibleapp.feature.books.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ChapterDto(
    @SerializedName("book_id")
    val id: String,
    val book: String,
    val chapter: Int,
    val url: String
)