package com.example.bibleapp.feature.bookMarks

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.bibleapp.BottomNavDestination
import com.example.bibleapp.feature.bookMarks.presentation.BookMarkReaderScreen
import com.example.bibleapp.feature.bookMarks.presentation.BookMarkScreen
import com.example.bibleapp.feature.bookMarks.presentation.BookMarkViewModel

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
                modifier = modifier,
                state = state,
                event = viewModel::onEvent,
                onNavigate = { bookId, chapter, verse ->
                    navController
                        .navigate(
                            route = BookMarkDestination
                                .BookMarkScreen
                                .createRoute(
                                    bookId = bookId,
                                    chapter = chapter,
                                    verse = verse
                                )
                        )
                }
            )
        }

        composable(
            route = BookMarkDestination.BookMarkScreen.route,
            arguments = listOf(
                navArgument("bookId") {
                    type = NavType.StringType
                },
                navArgument("chapter") {
                    type = NavType.IntType
                },
                navArgument("verse") {
                    type = NavType.IntType
                }
            )
        )
        { backStackEntry ->
            val bookId = backStackEntry
                .arguments
                ?.getString("bookId")
                ?: ""

            val chapter = backStackEntry
                .arguments
                ?.getInt("chapter")
                ?: 0

            val verse = backStackEntry
                .arguments
                ?.getInt("verse")
                ?: 0

            val viewModel: BookMarkViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            val selectedVerse = state
                .bookMarks
                .find {
                    it.id == bookId && it.chapter == chapter && it.verse == verse
                }

            BookMarkReaderScreen(
                state = state,
                event = viewModel::onEvent,
                bookMark = selectedVerse,
                onBackPress = {
                    navController.navigateUp()
                }
            )
        }
    }
}

sealed class BookMarkDestination(val route: String) {
    data object BookMarkListScreen : BookMarkDestination("book_mark_list_screen")

    data object BookMarkScreen : BookMarkDestination("verse_screen/{bookId}/{chapter}/{verse}") {
        fun createRoute(bookId: String, chapter: Int, verse: Int): String {
            return "verse_screen/$bookId/$chapter/$verse"
        }
    }
}