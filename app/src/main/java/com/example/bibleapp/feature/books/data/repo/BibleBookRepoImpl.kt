package com.example.bibleapp.feature.books.data.repo

import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.bible.data.remote.BibleApi
import com.example.bibleapp.feature.books.data.mapper.toDomain
import com.example.bibleapp.feature.books.domain.model.Books
import com.example.bibleapp.feature.books.domain.model.Chapters
import com.example.bibleapp.feature.books.domain.model.Verses
import com.example.bibleapp.feature.books.domain.repo.BibleBookRepo
import javax.inject.Inject

class BibleBookRepoImpl @Inject constructor(
    private val bibleBooksApi: BibleApi
) : BibleBookRepo {
    override suspend fun getBibleBooks(): BaseResult<Books?, Error> {
        val result = bibleBooksApi.getBooks()

        if (result.isSuccessful) {
            val body = result.body()
            return if (body != null) {
                BaseResult.Success(result.body()?.books?.toDomain())
            } else {
                BaseResult.Failure(Error("API Error ${result.code()}: ${result.message()}"))
            }
        } else {
            throw Exception(
                result.errorBody()?.string()
                    ?: "Unknown error occurred"
            )
        }
    }

    override suspend fun getBookChapters(bookId: String): BaseResult<Chapters, Error> {
        val result = bibleBooksApi.getChapters(bookId = bookId)

        return if (result.isSuccessful) {
            val body = result.body()

            if (body != null) {
                BaseResult.Success(body.chapters.toDomain())
            } else {
                BaseResult.Failure(Error("API Error ${result.code()}: ${result.message()}"))
            }
        } else {
            BaseResult.Failure(
                Error(
                    "API Error ${result.code()}: ${result.message()}"
                )
            )
        }
    }

    override suspend fun getVerses(bookId: String, chapter: Int): BaseResult<Verses, Error> {
        val result = bibleBooksApi.getVerses(bookId = bookId, chapter = chapter)

        return if (result.isSuccessful){
            val body = result.body()
            if (body != null) {
                BaseResult.Success(body.verses.toDomain())
            } else {
                BaseResult.Failure(Error("API Error ${result.code()}: ${result.message()}"))
            }
        } else {
            BaseResult.Failure(
                Error(
                    "API Error ${result.code()}: ${result.message()}"
                )
            )
        }
    }
}