package com.example.bibleapp.feature.books.domain.use_case

import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.books.domain.repo.BibleBookRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class GetBibleBooksUseCase @Inject constructor(
    private val bibleBookRepo: BibleBookRepo
) {
    operator fun invoke() = flow {
        emit(BaseResult.Loading())
        val books = bibleBookRepo.getBibleBooks()
        delay(2.seconds)

        if (books.books.isNotEmpty())
            emit(BaseResult.Success(books))
        else
            emit(BaseResult.Failure(Error("Empty file")))
    }.catch { exception ->
        Timber.e("FileError $exception")
        emit(BaseResult.Failure(Error(exception.message)))
    }
}