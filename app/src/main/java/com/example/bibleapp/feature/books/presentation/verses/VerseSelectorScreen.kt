package com.example.bibleapp.feature.books.presentation.verses

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.bibleapp.feature.books.domain.model.Verse
import com.example.bibleapp.feature.books.presentation.BibleBookEvent
import com.example.bibleapp.feature.books.presentation.BibleBookUiState
import com.example.bibleapp.feature.books.presentation.chapters.mockBibleBookUiState
import com.example.bibleapp.feature.books.presentation.components.BookChapterTopAppBar

@Composable
fun VerseSelectorScreen(
    modifier: Modifier = Modifier,
    state: BibleBookUiState,
    event: (BibleBookEvent) -> Unit,
    onNavigate: (Int) -> Unit,
    onBackPress: () -> Unit,
    onReadWholeVerse: () -> Unit
) {
    val bookId = state.currentBookId
    val bookName = state.bibleBooks?.books?.find { it.id == bookId }

    val chapter = state.currentChapter
    val title = "${bookName?.name}: $chapter"


    Scaffold(
        topBar = {
            BookChapterTopAppBar(
                title = title,
                onBackPress = onBackPress
            )
        },
        modifier = modifier
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
            if (state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                VerseSelectorList(
                    state = state,
                    event = event,
                    onNavigate = onNavigate,
                    onReadWholeVerse = onReadWholeVerse
                )
            }
        }


    }
}

@Composable
fun VerseSelectorList(
    modifier: Modifier = Modifier,
    state: BibleBookUiState,
    event: (BibleBookEvent) -> Unit,
    onNavigate: (Int) -> Unit,
    onReadWholeVerse: () -> Unit
) {

    val bookId = state.currentBookId
    val bookName = state.bibleBooks?.books?.find { it.id == bookId }?.name
    val chapter = state.currentChapter

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        state.verses?.verses?.let { verses ->
            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Pick a verse",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
            }

            items(
                items = verses,
                key = { it.verse }
            ) { verse ->
                VerseItem(
                    item = verse,
                    onClick = { onNavigate(verse.verse) }
                )
            }

            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = { onReadWholeVerse() }
                ) {
                    Text(
                        text = "Read Book of $bookName, Chapter $chapter",
                    )
                }
            }
        }

    }
}

@Composable
private fun VerseItem(
    modifier: Modifier = Modifier,
    item: Verse,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = { onClick(item.chapter) }),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${item.verse}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Composable
private fun VerseSelectorPreview() {
    VerseSelectorScreen(
        state = mockBibleBookUiState,
        event = {},
        onNavigate = {},
        onBackPress = {},
        onReadWholeVerse = {}
    )
}