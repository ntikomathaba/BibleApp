package com.example.bibleapp.feature.books.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.books.domain.use_case.GetBibleBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BibleBooksViewModel @Inject constructor(
    private val getBibleBooksUseCase: GetBibleBooksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BibleBookUiState())
    val state = _uiState.asStateFlow()

    init {
        getBooks()
    }

    fun onEvent(event: BibleBookEvent){
        when(event){
            BibleBookEvent.FetchBibleBooks -> getBooks()
        }
    }

    private fun getBooks() = viewModelScope.launch {
        getBibleBooksUseCase()
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