package com.example.bibleapp.feature.bible.domain.repo

import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.bible.domain.model.BibleVerse

interface BibleRepo {
    suspend fun getVerse(query: String): BaseResult<BibleVerse, Error>
}