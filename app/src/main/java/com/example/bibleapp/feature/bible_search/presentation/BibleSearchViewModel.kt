package com.example.bibleapp.feature.bible_search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibleapp.core.result.BaseResult
import com.example.bibleapp.feature.bible_search.domain.use_case.SearchBibleVerseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BibleSearchViewModel @Inject constructor(
    val getBibleVerseUseCase: SearchBibleVerseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BibleSearchUiState())
    val state = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")


    private val _eventsChannel = Channel<ValidationEvent>()
    val events = _eventsChannel.receiveAsFlow()

    init {
        observeSearch()
    }

    fun onEvent(event: BibleSearchEvent) {
        when (event) {
            is BibleSearchEvent.OnSearchQueryChanged -> onSearchQueryChanged(event.query)
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun observeSearch() {
        _searchQuery
            .debounce(300L)
            .distinctUntilChanged()
            .filter { it.isNotBlank() }
            .flatMapLatest { query ->
                getBibleVerseUseCase(query)
            }.onEach { result ->
                when (result) {
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
                        Timber.e("SearchResult: ${result.data}")
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                bibleVerse = result.data,
                            )
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun onSearchQueryChanged(query: String) = viewModelScope.launch {
        _uiState.update { state ->
            state.copy(
                searchQuery = query
            )
        }

        _searchQuery.update { query }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
        object Error : ValidationEvent()
    }
}

