package com.example.bibleapp.feature.books.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
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
fun BibleBookTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackPress: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookChapterTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackPress: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "navigation back arrow"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerseReaderTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onFavVerse:() -> Unit,
    onBackPress: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "navigation back arrow"
                )
            }
        },
        actions = {
            IconButton(onClick = onFavVerse) {
                Icon(Icons.Default.Favorite, contentDescription = "Cancel search")
            }
        }
    )
}