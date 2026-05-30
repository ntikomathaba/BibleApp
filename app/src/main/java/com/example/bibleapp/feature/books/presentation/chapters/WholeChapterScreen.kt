package com.example.bibleapp.feature.books.presentation.chapters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.bibleapp.feature.books.domain.model.Verse
import com.example.bibleapp.feature.books.presentation.BibleBookEvent
import com.example.bibleapp.feature.books.presentation.BibleBookUiState
import com.example.bibleapp.feature.books.presentation.components.BookChapterTopAppBar

@Composable
fun WholeChapterScreen(
    modifier: Modifier = Modifier,
    state: BibleBookUiState,
    event: (BibleBookEvent) -> Unit,
    onBackPress: () -> Unit
) {
    val bookId = state.currentBookId
    val bookName = state.bibleBooks.find { it.id == bookId }

    val chapter = state.currentChapter
    val title = "${bookName?.name}: $chapter"

    Scaffold(
        topBar = {
            BookChapterTopAppBar(
                title = title,
                onBackPress = onBackPress
            )
        }
    ) { innerPadding ->
        val contentPadding = PaddingValues(
            start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
            top = innerPadding.calculateTopPadding(),
            end = innerPadding.calculateEndPadding(LayoutDirection.Rtl),
            bottom = innerPadding.calculateBottomPadding()
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            WholeChapterList(
                state = state,
                event = event
            )
        }
    }
}

@Composable
fun WholeChapterList(
    modifier: Modifier = Modifier,
    state: BibleBookUiState,
    event: (BibleBookEvent) -> Unit
) {
    state.verses?.verses?.let { verses ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(
                items = verses,
                key = { it.verse }
            ) {
                VerseItem(
                    item = it,
                    onFavVerse = {
                        event(BibleBookEvent.FavVerse(it.verse))
                    }
                )
            }
        }
    }
}

@Composable
fun VerseItem(
    modifier: Modifier = Modifier,
    item: Verse,
    onFavVerse: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = item.verse.toString(),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .width(24.dp)
                .paddingFromBaseline(top = 20.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        IconButton(onClick = { onFavVerse() }) {
            Icon(Icons.Default.Favorite, contentDescription = "")
        }
    }
}

@Preview
@Composable
private fun WholeChapterScreenPreview() {
    WholeChapterScreen(
        state = mockBibleBookUiState,
        event = {},
        onBackPress = {}
    )
}