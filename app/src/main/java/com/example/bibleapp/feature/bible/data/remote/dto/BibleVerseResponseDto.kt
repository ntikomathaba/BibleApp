package com.example.bibleapp.feature.bible.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BibleVerseResponseDto(
    val reference: String,
    val verses: List<VerseDto>,
    val text: String,
    @SerializedName("translation_id")
    val translationId: String,
    @SerializedName("translation_name")
    val translationName: String,
    @SerializedName("translation_note")
    val translationNote: String
)

