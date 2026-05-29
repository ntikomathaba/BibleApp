package com.example.bibleapp.feature.bookMarks

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bibleapp.BottomNavDestination
import com.example.bibleapp.feature.bookMarks.presentation.BookMarkScreen
import com.example.bibleapp.feature.bookMarks.presentation.BookMarkViewModel
import com.example.bibleapp.feature.books.presentation.BibleBooksViewModel

fun NavGraphBuilder.bookMarksNavGraph(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = BottomNavDestination.BookMark.route,
        startDestination = BookMarkDestination.BookMarkListScreen.route
    ) {
        composable(
            route = BookMarkDestination.BookMarkListScreen.route
        ) {
            val viewModel: BookMarkViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            BookMarkScreen(
                state = state,
                event = viewModel::onEvent
            )
        }
    }
}

sealed class BookMarkDestination(val route: String){
    data object BookMarkListScreen: BookMarkDestination("book_mark_list_screen")
}