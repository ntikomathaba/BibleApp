package com.example.bibleapp.feature.verse_of_the_day.data.repo

import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.bible_search.data.remote.BibleApi
import com.example.bibleapp.feature.verse_of_the_day.data.mapper.toDomain
import com.example.bibleapp.feature.verse_of_the_day.domain.model.VerseOfTheDay
import com.example.bibleapp.feature.verse_of_the_day.domain.repo.VerseOfTheDayRepo
import javax.inject.Inject

class VerseOfTheDayImpl @Inject constructor(
    private val bibleBooksApi: BibleApi
) : VerseOfTheDayRepo {
    override suspend fun getVerseOfTheDay(): BaseResult<VerseOfTheDay?, Error> {
        val result = bibleBooksApi.getRandomVerse()

        if (result.isSuccessful) {
            val body = result.body()
            return if (body != null) {
                BaseResult.Success(body.verseOfTheDay.toDomain())
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
}