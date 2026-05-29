package com.example.bibleapp.feature.bible_search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.bible_search.domain.use_case.GetBibleVerseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BibleViewModel @Inject constructor(
    val getBibleVerseUseCase: GetBibleVerseUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(BibleUiState())
    val state = _uiState.asStateFlow()

    private val _eventsChannel = Channel<ValidationEvent>()
    val events = _eventsChannel.receiveAsFlow()

    fun onEvent(event: BibleEvent){
        when(event){
            is BibleEvent.GetBibleVerse -> getBibleVerse(event.query)
        }
    }

    private fun getBibleVerse(query: String) = viewModelScope.launch {
        getBibleVerseUseCase
            .invoke(query)
            .collect { result ->
                when(result){
                    is BaseResult.Failure -> {
                        _eventsChannel.send(ValidationEvent.Error)
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
                        Timber.e("BibleResult: ${result.data}")
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                bibleVerse = result.data,
                            )
                        }
                    }
                }
            }

    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
        object Error : ValidationEvent()
    }
}

