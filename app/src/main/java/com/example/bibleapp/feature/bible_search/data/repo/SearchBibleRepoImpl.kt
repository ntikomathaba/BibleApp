package com.example.bibleapp.feature.bible_search.data.repo

import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.bible_search.data.mapper.toDomain
import com.example.bibleapp.feature.bible_search.data.remote.BibleApi
import com.example.bibleapp.feature.bible_search.domain.repo.SearchBibleRepo
import com.example.bibleapp.feature.bible_search.domain.model.BibleVerse
import timber.log.Timber
import javax.inject.Inject

class SearchBibleRepoImpl @Inject constructor(
    private val api: BibleApi
) : SearchBibleRepo {
    override suspend fun searchBible(query: String): BaseResult<BibleVerse, Error> {
        val result = api.getVerse(query)
        if (result.isSuccessful) {
            val body = result.body()
            return if (body != null) {
//                Timber.e("SearchResult $body")
                BaseResult.Success(body.toDomain())
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