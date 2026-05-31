package com.example.bibleapp.feature.bible_search.presentation.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.bibleapp.feature.bible_search.presentation.BibleSearchEvent
import com.example.bibleapp.feature.bible_search.presentation.BibleSearchUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BibleSearchTopAppBar(
    modifier: Modifier = Modifier,
    placeHolderText: String,
    state: BibleSearchUiState,
    event: (BibleSearchEvent) -> Unit,
    onBackPress: () -> Unit
) {
    TopAppBar(
        title = {
            TextField(
                value = state.searchQuery,
                onValueChange = { event(BibleSearchEvent.OnSearchQueryChanged(it)) },
                placeholder = {
                    Text(text = placeHolderText)
                },
                singleLine = true,
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.tertiary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.outlineVariant,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.outlineVariant,
                )
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
            IconButton(onClick = onBackPress) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }
        }
    )
}