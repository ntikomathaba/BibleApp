package com.example.bibleapp.feature.verse_of_the_day.domain.use_case

import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.verse_of_the_day.domain.repo.VerseOfTheDayRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class VOTDUseCases @Inject constructor(
    val getVerseOfTheDayUseCase: GetVerseOfTheDayUseCase
)

class GetVerseOfTheDayUseCase @Inject constructor(
    private val repo: VerseOfTheDayRepo
) {
    operator fun invoke() = flow {
        emit(BaseResult.Loading())
        when (val result = repo.getVerseOfTheDay()) {
            is BaseResult.Success -> {
                emit(BaseResult.Success(result.data))
            }
            is BaseResult.Failure -> {
                emit(BaseResult.Failure(result.error))
            }
            is BaseResult.Loading -> Unit
        }
    }
}