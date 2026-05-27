package com.example.bibleapp.feature.bible.data.repo

import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.bible.data.mapper.toDomain
import com.example.bibleapp.feature.bible.data.remote.BibleApi
import com.example.bibleapp.feature.bible.domain.repo.BibleRepo
import com.example.bibleapp.feature.bible.domain.model.BibleVerse
import javax.inject.Inject

class BibleRepoImpl @Inject constructor(
    private val api: BibleApi
) : BibleRepo {
    override suspend fun getVerse(query: String): BaseResult<BibleVerse, Error> {
        val result = api.getVerse(query)
        if (result.isSuccessful) {
            val body = result.body()
            return if (body != null) {
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