package com.example.bibleapp.feature.books.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TranslationDto(
    val identifier: String,
    val name: String,
    val language: String,
    @SerializedName("language_code")
    val languageCode: String,
    val license: String
)