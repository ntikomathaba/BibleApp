package com.example.bibleapp.feature.books.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.books.domain.use_case.BibleUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BibleBooksViewModel @Inject constructor(
    private val bibleUseCases: BibleUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(BibleBookUiState())
    val state = _uiState.asStateFlow()

    init {
        getBooks()
    }

    fun onEvent(event: BibleBookEvent) {
        when (event) {
            is BibleBookEvent.FetchBibleBooks -> getBooks()
            is BibleBookEvent.OnClickBook -> onClickBook(event.bookId)
            is BibleBookEvent.GetChapters -> getChapters(event.bookId)
            is BibleBookEvent.GetVerses -> getVerses(event.bookId, event.chapter)
        }
    }

    private fun getVerses(bookId: String, chapter: Int) = viewModelScope.launch {
        Timber.e("Path: $bookId/$chapter")
        bibleUseCases
            .getVersesUseCase(bookId, chapter)
            .collect { result ->
                when (result) {
                    is BaseResult.Failure -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                isError = true,
                                errorMessage = result.error.message
                            )
                        }
                    }

                    is BaseResult.Loading -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = true
                            )
                        }
                    }

                    is BaseResult.Success -> {
                        Timber.e("Verses: ${result.data}")
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                verses = result.data,
                                bookId = bookId,
                                chapter = chapter
                            )
                        }
                    }
                }
            }
    }

    private fun getChapters(bookId: String) = viewModelScope.launch {
        bibleUseCases
            .getChaptersUseCase(bookId)
            .collect { result ->
                when (result) {
                    is BaseResult.Failure -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                isError = true,
                                errorMessage = result.error.message
                            )
                        }
                    }

                    is BaseResult.Loading -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = true
                            )
                        }
                    }

                    is BaseResult.Success -> {
                        Timber.e("Chapters ${result.data}")
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                chapters = result.data,
                                bookId = bookId
                            )
                        }
                    }
                }
            }
    }

    private fun onClickBook(bookId: String) {
        Timber.e("BookId: $bookId")
        _uiState.update { state ->
            state.copy(
                bookId = bookId
            )
        }
    }

    private fun getBooks() = viewModelScope.launch {
        bibleUseCases
            .getBibleBooksUseCase()
            .collect { result ->
                when (result) {
                    is BaseResult.Failure -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                isError = true,
                                errorMessage = result.error.message
                            )
                        }
                    }

                    is BaseResult.Loading -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = true
                            )
                        }
                    }

                    is BaseResult.Success -> {
                        Timber.e("Books ${result.data}")
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                bibleBooks = result.data
                            )
                        }
                    }
                }
            }
    }


}