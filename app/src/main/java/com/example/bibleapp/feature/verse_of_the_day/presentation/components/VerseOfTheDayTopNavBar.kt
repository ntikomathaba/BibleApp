package com.example.bibleapp.feature.verse_of_the_day.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerseOfTheDayTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onFavVerse: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        actions = {
            IconButton(onClick = onFavVerse) {
                Icon(Icons.Default.Favorite, contentDescription = "Save Verse")
            }
        }
    )
}