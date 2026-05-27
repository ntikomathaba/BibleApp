package com.example.bibleapp.feature.bible.data.remote

import com.example.bibleapp.feature.bible.data.remote.dto.BibleVerseResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BibleApi {
    @GET("{query}")
    suspend fun getVerse(
        @Path("query") query: String
    ): Response<BibleVerseResponseDto>
}