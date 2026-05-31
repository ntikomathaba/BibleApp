package com.example.bibleapp.feature.bible_search.domain.use_case

import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.bible_search.domain.repo.SearchBibleRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class SearchBibleVerseUseCase @Inject constructor(
    val bibleRepo: SearchBibleRepo
) {
    operator fun invoke(query: String) = flow {
        emit(BaseResult.Loading())
        emit(
            bibleRepo.searchBible(query)
        )
    }.catch { exception ->
        Timber.e("Exception ${exception.message}")
    }
}