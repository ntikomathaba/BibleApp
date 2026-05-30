package com.example.bibleapp.feature.verse_of_the_day.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.bookMarks.domain.use_case.FavouriteVerseUseCases
import com.example.bibleapp.feature.books.data.mapper.toDomain
import com.example.bibleapp.feature.verse_of_the_day.data.mapper.toEntity
import com.example.bibleapp.feature.verse_of_the_day.domain.model.VerseOfTheDay
import com.example.bibleapp.feature.verse_of_the_day.domain.use_case.VOTDUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerseOfTheDayViewModel @Inject constructor(
    private val votdUseCases: VOTDUseCases,
    private val favouriteVerseUseCases: FavouriteVerseUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(VOTDUiState())
    val state = _uiState.asStateFlow()

    fun onEvent(event: VOTDEvent) {
        when (event) {
            is VOTDEvent.GetVerseOfTheDay -> getVerseOfTheDay()
            is VOTDEvent.FavVerse -> favVerse()
        }
    }

    private fun favVerse() = viewModelScope.launch {
        val verse = _uiState.value.verse.toEntity()
        favouriteVerseUseCases
            .favVerseUse(verse.toDomain())
    }

    private fun getVerseOfTheDay() = viewModelScope.launch {
        votdUseCases
            .getVerseOfTheDayUseCase()
            .collect { result ->
                when (result) {
                    is BaseResult.Failure -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                errorMessage = result.error.message,
                                isError = true
                            )
                        }
                    }

                    is BaseResult.Loading -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = true,
                            )
                        }
                    }

                    is BaseResult.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                verse = result.data ?: VerseOfTheDay()
                            )
                        }
                    }
                }
            }
    }
}