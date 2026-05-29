package com.example.bibleapp.feature.bookMarks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibleapp.feature.bookMarks.domain.mapper.toDomain
import com.example.bibleapp.feature.bookMarks.domain.use_case.FavouriteVerseUseCases
import com.example.bibleapp.feature.books.data.mapper.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val favouriteVerseUseCases: FavouriteVerseUseCases
): ViewModel() {

    private val _uiState = MutableStateFlow(BookMarkUiState())
    val state = _uiState.asStateFlow()

    init {
        fetchVerses()
    }

    fun onEvent(event: BookMarkEvent){
//        when(event){
//
//        }
    }

    private fun fetchVerses() = viewModelScope.launch {
        favouriteVerseUseCases
            .fetchFavouriteVersesUseCase()
            .collect { verses ->
                _uiState.update { state ->
                    state.copy(
                        bookMarks = verses.toDomain()
                    )
                }
            }
    }
}