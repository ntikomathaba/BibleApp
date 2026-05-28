package com.example.bibleapp.feature.books.domain.use_case

import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.books.domain.repo.BibleBookRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class GetVersesUseCase @Inject constructor(
    private val bibleBookRepo: BibleBookRepo
) {
    operator fun invoke(bookId: String, chapter: Int) = flow {
        emit(BaseResult.Loading())

        when (val result = bibleBookRepo.getVerses(bookId, chapter)) {
            is BaseResult.Success -> {
                emit(BaseResult.Success(result.data))
            }
            is BaseResult.Failure -> {
                emit(BaseResult.Failure(result.error))
            }
            is BaseResult.Loading -> Unit
        }
    }.catch { exception ->
        Timber.e("APIError $exception")
        emit(BaseResult.Failure(Error(exception.message)))
    }
}