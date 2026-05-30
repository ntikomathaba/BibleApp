package com.example.bibleapp.feature.books.presentation.books

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import com.example.bibleapp.feature.books.domain.model.Chapter
import com.example.bibleapp.feature.books.presentation.BibleBookEvent
import com.example.bibleapp.feature.books.presentation.BibleBookUiState
import timber.log.Timber

@Composable
fun WholeChapterScreen(
    modifier: Modifier = Modifier,
    bookId: String,
    state: BibleBookUiState,
    event: (BibleBookEvent) -> Unit
) {
    Scaffold(
        topBar = {

        }
    ) { innerPadding ->
        val contentPadding = PaddingValues(
            start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
            top = innerPadding.calculateTopPadding(),
            end = innerPadding.calculateEndPadding(LayoutDirection.Rtl),
            bottom = innerPadding.calculateBottomPadding()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Timber.e("BibleState: $state")
            WholeBookList(
                state = state,
                event = event
            )
        }
    }
}

@Composable
fun WholeBookList(
    modifier: Modifier = Modifier,
    state: BibleBookUiState,
    event: (BibleBookEvent) -> Unit
) {
    val wholeBook = state.chapters?.chapters ?: emptyList()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(
            items = wholeBook,
            key = {
                it.url
            }
        ) {
            BookItem(
                chapter = it
            )
        }
    }
}

@Composable
fun BookItem(
    modifier: Modifier = Modifier,
    chapter: Chapter,
) {
    Text(
        text = chapter.book
    )
}