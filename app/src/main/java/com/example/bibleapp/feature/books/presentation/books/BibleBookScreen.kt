package com.example.bibleapp.feature.books.presentation.books

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bibleapp.R
import com.example.bibleapp.feature.books.domain.model.BibleBook
import com.example.bibleapp.feature.books.domain.model.Testament
import com.example.bibleapp.feature.books.presentation.BibleBookEvent
import com.example.bibleapp.feature.books.presentation.BibleBookUiState
import com.example.bibleapp.feature.books.presentation.components.BibleBookTopAppBar

@Composable
fun BibleBookScreen(
    modifier: Modifier = Modifier,
    state: BibleBookUiState,
    event: (BibleBookEvent) -> Unit,
    onNavigate: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        event(BibleBookEvent.FetchBibleBooks)
    }

    Scaffold(
        topBar = {
            BibleBookTopAppBar(
                title = stringResource(R.string.bible_books),
                onBackPress = {}
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
                BooksList(
                    state = state,
                    event = event,
                    onNavigate = onNavigate
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BooksList(
    modifier: Modifier = Modifier,
    state: BibleBookUiState,
    event: (BibleBookEvent) -> Unit,
    onNavigate: (String) -> Unit
) {
    val oldTestamentBooks = state.bibleBooks.filter {
        it.testament == Testament.OLD
    }

    val newTestamentBooks = state.bibleBooks.filter {
        it.testament == Testament.NEW
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        stickyHeader {
            TestamentHeader(
                title = "Old Testament"
            )
        }

        itemsIndexed(
            items = oldTestamentBooks,
            key = { _, item ->
                item.id
            }
        ) { index, book ->
            BookItem(
                item = book,
                onClick = {
                    event(BibleBookEvent.OnClickBook(book.id))
                    onNavigate(book.id)
                },
                isLastItem = index == oldTestamentBooks.lastIndex
            )
        }

        stickyHeader {
            TestamentHeader(
                title = "New Testament"
            )
        }

        itemsIndexed(
            items = newTestamentBooks,
            key = { _, item ->
                item.id
            }
        ) { index, book ->
            BookItem(
                item = book,
                onClick = {
                    event(BibleBookEvent.OnClickBook(book.id))
                    onNavigate(book.id)
                },
                isLastItem = index == oldTestamentBooks.lastIndex
            )
        }
    }
}

@Composable
fun BookItem(
    modifier: Modifier = Modifier,
    item: BibleBook,
    isLastItem: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults
            .cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = item.id,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Chevron",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
    if (!isLastItem) {
        HorizontalDivider()
    }
}

@Composable
fun TestamentHeader(
    modifier: Modifier = Modifier,
    title: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp)
        )
    }
}