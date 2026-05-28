package com.example.bibleapp.feature.books.presentation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.bibleapp.Destination
import com.example.bibleapp.feature.books.presentation.books.BibleBookScreen
import com.example.bibleapp.feature.books.presentation.chapters.ChapterScreen
import com.example.bibleapp.feature.books.presentation.verses.VersesScreen

fun NavGraphBuilder.bibleBookNavGraph(
    navController: NavController,
) {
    navigation(
        route = Destination.BibleBookNavGraph.route,
        startDestination = BibleBookDestination.BibleBookListScreen.route
    ) {
        composable(BibleBookDestination.BibleBookListScreen.route) {
            val viewModel: BibleBooksViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            BibleBookScreen(
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

                }
            )

        }
    }
}

sealed class BibleBookDestination(val route: String) {
    data object BibleBookListScreen : BibleBookDestination("bible_book_list_screen")
    data object BibleBookScreen :
        BibleBookDestination(
            route = "bible_book/{bookId}"
        ) {

        fun createRoute(
            bookId: String
        ): String {
            return "bible_book/$bookId"
        }
    }

    data object BibleVersesScreen :
        BibleBookDestination(
            "bible_verses/{bookId}/{chapter}"
        ) {

        fun createRoute(
            bookId: String,
            chapter: Int
        ): String {

            return "bible_verses/$bookId/$chapter"
        }
    }
}