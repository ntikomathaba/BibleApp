package com.example.bibleapp.feature.books.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.bookMarks.domain.use_case.FavouriteVerseUseCases
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
    private val bibleUseCases: BibleUseCases,
    private val favouriteVerseUseCases: FavouriteVerseUseCases
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
            is BibleBookEvent.OnClickChapter -> onClickChapter(event.chapter)
            is BibleBookEvent.OnChangeVerse -> onCurrentVerse(event.currentVerse)
            is BibleBookEvent.FavVerse -> favVerse(event.verse)
        }
    }

    private fun onCurrentVerse(currentVerse: Int) {
        _uiState.update { state ->
            state.copy(
                currentVerse = currentVerse
            )
        }
    }


    private fun favVerse(verseNum: Int) = viewModelScope.launch {
        val foundVerse = _uiState.value.verses?.verses?.find { it.verse == verseNum }
        Timber.e("SavingVerse $foundVerse")

        favouriteVerseUseCases
            .favVerseUse(foundVerse)
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
                                currentBookId = bookId,
                                currentChapter = chapter
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
                                currentBookId = bookId
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
                currentBookId = bookId
            )
        }
    }

    private fun onClickChapter(chapter: Int) {
        _uiState.update { state ->
            state.copy(
                currentChapter = chapter
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