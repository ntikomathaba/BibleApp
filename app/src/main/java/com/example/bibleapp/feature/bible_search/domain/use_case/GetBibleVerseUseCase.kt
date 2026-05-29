package com.example.bibleapp.feature.bible_search.domain.use_case

import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.bible_search.domain.repo.BibleRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class GetBibleVerseUseCase @Inject constructor(
    val bibleRepo: BibleRepo
) {
    operator fun invoke(query: String) = flow {
        emit(BaseResult.Loading())
        val result = bibleRepo.getVerse(query)
        if (result.isSuccess()) {
            emit(result)
        } else if (result.isFailure()) {
            emit(result)
        }
    }.catch { exception ->
        Timber.e("Error: $exception")
        emit(BaseResult.Failure(Error(exception)))
    }
}