package com.example.bibleapp.feature.books.presentation.verses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.bibleapp.feature.books.domain.model.Verse
import com.example.bibleapp.feature.books.presentation.BibleBookEvent
import com.example.bibleapp.feature.books.presentation.BibleBookUiState
import com.example.bibleapp.feature.books.presentation.components.BookChapterTopAppBar
import kotlinx.coroutines.launch

@Composable
fun VerseReaderScreen(
    modifier: Modifier = Modifier,
    state: BibleBookUiState,
    event: (BibleBookEvent) -> Unit,
    bookId: String,
    chapter: Int,
    verse: Int,
    onBackPress: () -> Unit,
) {

    val title = "$bookId $chapter:$verse"

    LaunchedEffect(
        key1 = bookId,
        key2 = chapter,
    ) {
        event(BibleBookEvent.GetVerses(bookId, chapter))
    }

    Scaffold(
        topBar = {
            BookChapterTopAppBar(
                title = title,
                onBackPress = onBackPress
            )
        },
        modifier = modifier,
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
            VerseReaderPager(
                modifier = Modifier.fillMaxSize(),
                state = state,
                event = event,
                initialVerse = verse,
            )
        }
    }
}

@Composable
fun VerseReaderPager(
    modifier: Modifier = Modifier,
    state: BibleBookUiState,
    event: (BibleBookEvent) -> Unit,
    initialVerse: Int,
) {
    val verses = state.verses?.verses
    val initialPage = (initialVerse - 1).coerceAtLeast(0)

    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { verses?.size ?: 0 }
    )

    val isFirstPage = pagerState.currentPage == 0
    val isLastPage = pagerState.currentPage == (verses?.size ?: 1) - 1

    val scope = rememberCoroutineScope()

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isFirstPage) {
            PagerNavButton(
                direction = PagerNavDirection.Previous,
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            }
        }

        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState
        ) { pageNum ->
            val currentVerse = verses?.get(pageNum)

            VersePage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verse = currentVerse,
                indicatorText = "${pageNum + 1}/${verses?.size}"
            )
        }

        if (!isLastPage) {
            PagerNavButton(
                direction = PagerNavDirection.Next,
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        }
    }
}

@Composable
fun VersePage(
    modifier: Modifier = Modifier,
    verse: Verse?,
    indicatorText: String
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${verse?.verse}",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = indicatorText,
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "${verse?.text}",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}


enum class PagerNavDirection { Previous, Next }

@Composable
private fun PagerNavButton(
    direction: PagerNavDirection,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val icon = if (direction == PagerNavDirection.Previous)
        Icons.Default.ChevronLeft
    else
        Icons.Default.ChevronRight

    val description = if (direction == PagerNavDirection.Previous)
        "Previous verse" else "Next verse"

    FilledTonalIconButton(
        onClick = onClick,
        modifier = modifier.size(40.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description
        )
    }
}

