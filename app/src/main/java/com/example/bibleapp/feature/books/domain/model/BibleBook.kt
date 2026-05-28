package com.example.bibleapp.feature.books.domain.model

import com.google.gson.annotations.SerializedName

data class BibleBook(
    @SerializedName("book")
    val name: String,
    val chapters: Int,
    val verses: Int
)