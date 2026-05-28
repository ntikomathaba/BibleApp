package com.example.bibleapp.feature.books.presentation

import androidx.compose.runtime.LaunchedEffect
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
import com.example.bibleapp.feature.books.presentation.books.BibleBookScreen
import com.example.bibleapp.feature.books.presentation.chapters.ChapterScreen
import com.example.bibleapp.feature.books.presentation.verses.VersesScreen

fun NavGraphBuilder.bibleBookNavGraph(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = BottomNavDestination.Bible.route,
        startDestination = BibleBookDestination.BibleBookListScreen.route
    ) {
        composable(BibleBookDestination.BibleBookListScreen.route) {
            val viewModel: BibleBooksViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            BibleBookScreen(
                modifier = modifier,
                state = state,
                event = viewModel::onEvent,
                onNavigate = { bookId ->
                    navController.navigate(
                        BibleBookDestination
                            .BibleBookScreen
                            .createRoute(bookId)
                    )
                }
            )
        }

        composable(
            route = BibleBookDestination.BibleBookScreen.route,
            arguments = listOf(
                navArgument("bookId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val bookId = backStackEntry
                .arguments
                ?.getString("bookId")
                ?: ""

            val viewModel: BibleBooksViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(bookId) {
                viewModel.onEvent(BibleBookEvent.GetChapters(bookId))
            }

            ChapterScreen(
                modifier = modifier,
                state = state,
                event = viewModel::onEvent,
                onNavigate = { chapter ->
                    navController.navigate(
                        BibleBookDestination
                            .BibleVersesScreen
                            .createRoute(
                                bookId = bookId,
                                chapter = chapter
                            )
                    )
                },
                onBackPress = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = BibleBookDestination.BibleVersesScreen.route,
            arguments = listOf(
                navArgument("bookId") {
                    type = NavType.StringType
                },
                navArgument("chapter") {
                    type = NavType.IntType
                }
            )
        ){ backStackEntry ->
            val bookId = backStackEntry
                .arguments
                ?.getString("bookId")
                ?: ""

            val chapter = backStackEntry
                .arguments
                ?.getInt("chapter")
                ?: 0

            val viewModel: BibleBooksViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(
                key1 = bookId,
                key2 = chapter
            ) {
                viewModel.onEvent(
                    BibleBookEvent.GetVerses(
                        bookId = bookId,
                        chapter = chapter
                    )
                )
            }

            VersesScreen(
                state = state,
                event = viewModel::onEvent,
                onBackPress = {
                    navController.navigateUp()
                },
                onNavigate = {

                },
                modifier = modifier
            )

        }
    }
}

sealed class BibleBookDestination(val route: String) {
    data object BibleBookListScreen : BibleBookDestination("bible_book_list_screen")
    data object BibleBookScreen : BibleBookDestination(route = "bible_book/{bookId}") {
        fun createRoute(bookId: String): String {
            return "bible_book/$bookId"
        }
    }

    data object BibleVersesScreen : BibleBookDestination("bible_verses/{bookId}/{chapter}") {
        fun createRoute(bookId: String, chapter: Int): String {
            return "bible_verses/$bookId/$chapter"
        }
    }
}