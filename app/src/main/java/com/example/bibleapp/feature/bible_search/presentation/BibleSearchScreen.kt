package com.example.bibleapp.feature.bible_search.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.bibleapp.R
import com.example.bibleapp.feature.bible_search.domain.model.Verse
import com.example.bibleapp.feature.bible_search.presentation.components.BibleSearchTopAppBar
import timber.log.Timber

@Composable
fun BibleSearchScreen(
    modifier: Modifier = Modifier,
    state: BibleSearchUiState,
    event: (BibleSearchEvent) -> Unit,
    onBackPress: () -> Unit
) {

    LaunchedEffect(Unit) {
//        event(BibleSearchEvent.GetBibleVerse("john 3:16"))
    }

    Scaffold(
        topBar = {
            BibleSearchTopAppBar(
                state = state,
                event = event,
                onBackPress = onBackPress,
                placeHolderText = stringResource(R.string.search)
            )
        }
    ) { innerPadding ->

        Timber.e("FoundVerse: ${state.bibleVerse?.text}")

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
            when {
                // EMPTY SEARCH
                state.searchQuery.isBlank() -> {
                    EmptySearchState()
                }

                // NO RESULTS
                state.bibleVerse == null -> {
                    InvalidSearchState()
                }

                state.searchQuery.isVerseSearch() -> {
                    SingleVerse(
                        verse = state.bibleVerse.text
                    )
                }

                state.searchQuery.isChapterSearch() -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        itemsIndexed(
                            items = state.bibleVerse.verses,
                            key = { index, item ->
                                "${item.bookName}_${item.chapter}_${item.verse}"
                            }
                        ) { index, item ->
                            ChapterVersesItem(
                                verse = item,
                                modifier = Modifier.fillMaxWidth(),
                                isLastItem = index == state.bibleVerse.verses.size - 1
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SingleVerse(
    modifier: Modifier = Modifier,
    verse: String
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = verse,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun ChapterVersesItem(
    modifier: Modifier = Modifier,
    verse: Verse,
    isLastItem: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "${verse.verse}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = verse.text
        )
    }

    if (!isLastItem) {
        HorizontalDivider()
    }
}

@Composable
fun EmptySearchState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Empty search state",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun InvalidSearchState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Enter a valid search",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

fun String.isVerseSearch(): Boolean {
    return Regex("^[1-3]?\\s?[A-Za-z]+(?:\\s[A-Za-z]+)*\\s\\d+:\\d+$")
        .matches(this.trim())
}

fun String.isChapterSearch(): Boolean {
    return Regex("^[1-3]?\\s?[A-Za-z]+(?:\\s[A-Za-z]+)*\\s\\d+$")
        .matches(this.trim())
}

