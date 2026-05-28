package com.example.bibleapp.feature.books.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BibleBookScreen(
    modifier: Modifier = Modifier,
    state: BibleBookUiState,
    event: (BibleBookEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        event(BibleBookEvent.FetchBibleBooks)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Works!"
        )
    }
}