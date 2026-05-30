package com.example.bibleapp.feature.verse_of_the_day.domain.repo

import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.verse_of_the_day.domain.model.VerseOfTheDay

interface VerseOfTheDayRepo {
    suspend fun getVerseOfTheDay(): BaseResult<VerseOfTheDay?, Error>
}