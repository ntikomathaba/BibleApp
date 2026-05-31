package com.example.bibleapp.feature.bible_search.domain.repo

import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.bible_search.domain.model.BibleVerse

interface SearchBibleRepo {
    suspend fun searchBible(query: String): BaseResult<BibleVerse, Error>
}