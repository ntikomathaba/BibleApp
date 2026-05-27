package com.example.bibleapp.feature.bible.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BibleScreen(
    modifier: Modifier = Modifier,
    state: BibleUiState,
    event: (BibleEvent) -> Unit
) {

    LaunchedEffect(Unit) {
        event(BibleEvent.GetBibleVerse("john 3:16"))
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