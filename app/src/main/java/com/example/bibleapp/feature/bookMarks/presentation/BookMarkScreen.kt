package com.example.bibleapp.feature.bookMarks.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.bibleapp.R
import com.example.bibleapp.feature.bookMarks.presentation.components.BookMarksTopAppBar
import com.example.bibleapp.feature.books.domain.model.Verse

@Composable
fun BookMarkScreen(
    modifier: Modifier = Modifier,
    state: BookMarkUiState,
    event: (BookMarkEvent) -> Unit
) {
    Scaffold(
        topBar = {
            BookMarksTopAppBar(
                title = stringResource(R.string.book_marks),
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val contentPadding = PaddingValues(
                start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                top = innerPadding.calculateTopPadding(),
                end = innerPadding.calculateEndPadding(LayoutDirection.Rtl),
                bottom = innerPadding.calculateBottomPadding()
            )

            BookMarksList(
                modifier = Modifier.padding(contentPadding),
                bookMarks = state.bookMarks,
                event = event
            )
        }
    }
}

@Composable
fun BookMarksList(
    modifier: Modifier = Modifier,
    bookMarks: List<Verse>,
    event: (BookMarkEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        itemsIndexed(
            items = bookMarks,
            key = { _, verse ->
                "${verse.id}_${verse.chapter}_${verse.verse}"
            }
        ) { index, bookMark ->
            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = { value ->
                    if (value == SwipeToDismissBoxValue.EndToStart) {
                        event(BookMarkEvent.OnDeleteBookMark(bookMark))

                        true
                    } else {
                        false
                    }
                },
                positionalThreshold = { it * 0.7f }
            )

            SwipeToDismissBox(
                state = dismissState,
                backgroundContent = { SwipeDeleteBackground(dismissState) },
                enableDismissFromStartToEnd = false,
                enableDismissFromEndToStart = true
            ) {
                BookMarkItem(
                    modifier = Modifier.animateItem(),
                    bookMark = bookMark,
                    index = index,
                    isLastItem = index == bookMarks.size - 1,
                    onClick = {

                    }
                )
            }
        }
    }
}

@Composable
fun BookMarkItem(
    modifier: Modifier = Modifier,
    bookMark: Verse,
    index: Int,
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
                .fillMaxWidth(),
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
                    text = "${index + 1}",
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
                    text = "${bookMark.id} ${bookMark.chapter}:${bookMark.verse}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = bookMark.text,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
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
fun SwipeDeleteBackground(
    swipeDismissState: SwipeToDismissBoxState
) {
    val color = if (swipeDismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
        MaterialTheme.colorScheme.error
    } else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Rounded.DeleteOutline,
            contentDescription = "Delete",
            tint = MaterialTheme.colorScheme.surface,
            modifier = Modifier.padding(end = 24.dp)
        )
    }
}