package com.example.bibleapp.feature.bible.data.remote

import com.example.bibleapp.feature.bible.data.remote.dto.BibleVerseResponseDto
import com.example.bibleapp.feature.books.data.remote.dto.BibleBooksResponseDto
import com.example.bibleapp.feature.books.data.remote.dto.BookChaptersResponseDto
import com.example.bibleapp.feature.books.data.remote.dto.VersesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BibleApi {
    @GET("{query}")
    suspend fun getVerse(
        @Path("query") query: String
    ): Response<BibleVerseResponseDto>

    @GET("data/{translation}")
    suspend fun getBooks(
        @Path("translation") translation: String = "web"
    ): Response<BibleBooksResponseDto>

    @GET("data/{translation}/{bookId}")
    suspend fun getChapters(
        @Path("translation") translation: String = "web",
        @Path("bookId") bookId: String
    ): Response<BookChaptersResponseDto>

    @GET("data/{translation}/{bookId}/{chapter}")
    suspend fun getVerses(
        @Path("translation") translation: String = "web",
        @Path("bookId") bookId: String,
        @Path("chapter") chapter: Int
    ): Response<VersesResponseDto>

}