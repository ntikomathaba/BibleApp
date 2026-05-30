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
import com.example.bibleapp.feature.books.presentation.chapters.WholeChapterScreen
import com.example.bibleapp.feature.books.presentation.chapters.ChapterScreen
import com.example.bibleapp.feature.books.presentation.verses.VerseReaderScreen
import com.example.bibleapp.feature.books.presentation.verses.VerseSelectorScreen
import timber.log.Timber

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
                    // user selects book
                    navController.navigate(
                        route = BibleBookDestination
                            .ChapterListScreen
                            .createRoute(bookId)
                    )
                }
            )
        }

        composable(
            route = BibleBookDestination.ChapterListScreen.route,
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
                    // user selects chapter
                    navController.navigate(
                        route = BibleBookDestination
                            .VerseListScreen
                            .createRoute(
                                bookId = bookId,
                                chapter = chapter
                            )
                    )
                },
                onBackPress = {
                    navController.navigateUp()
                },
            )
        }

        composable(
            route = BibleBookDestination.WholeChapterScreen.route,
            arguments = listOf(
                navArgument("bookId") {
                    type = NavType.StringType
                },
                navArgument("chapter") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
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

            WholeChapterScreen(
                modifier = modifier,
                state = state,
                event = viewModel::onEvent,
                onBackPress = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = BibleBookDestination.VerseListScreen.route,
            arguments = listOf(
                navArgument("bookId") {
                    type = NavType.StringType
                },
                navArgument("chapter") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
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

            VerseSelectorScreen(
                state = state,
                event = viewModel::onEvent,
                onBackPress = {
                    navController.navigateUp()
                },
                onNavigate = { verse ->
                    // user selects verse
                    navController
                        .navigate(
                            route = BibleBookDestination
                                .VerseScreen
                                .createRoute(
                                    bookId = bookId,
                                    chapter = chapter,
                                    verse = verse
                                )
                        )
                },
                onReadWholeVerse = {
                    Timber.e("NavigationPath $bookId/$chapter")
                    navController.navigate(
                        route = BibleBookDestination
                            .WholeChapterScreen
                            .createRoute(
                                bookId = bookId,
                                chapter = chapter
                            )
                    )

                },
                modifier = modifier
            )
        }

        composable(
            route = BibleBookDestination.VerseScreen.route,
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
        ) { backStackEntry ->
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

            val viewModel: BibleBooksViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            VerseReaderScreen(
                state = state,
                event = viewModel::onEvent,
                bookId = bookId,
                chapter = chapter,
                verseNum = verse,
                onBackPress = {
                    navController.navigateUp()
                }
            )
        }
    }
}

sealed class BibleBookDestination(val route: String) {
    data object BibleBookListScreen : BibleBookDestination("bible_book_list_screen")
    data object ChapterListScreen : BibleBookDestination("chapter_list/{bookId}") {
        fun createRoute(bookId: String): String {
            return "chapter_list/$bookId"
        }
    }

    data object WholeChapterScreen : BibleBookDestination("whole_book_screen/{bookId}/{chapter}") {
        fun createRoute(bookId: String, chapter: Int): String {
            return "whole_book_screen/$bookId/$chapter"
        }
    }

    data object VerseListScreen : BibleBookDestination("verse_list/{bookId}/{chapter}") {
        fun createRoute(bookId: String, chapter: Int): String {
            return "verse_list/$bookId/$chapter"
        }
    }

    data object VerseScreen : BibleBookDestination("verse_screen/{bookId}/{chapter}/{verse}") {
        fun createRoute(bookId: String, chapter: Int, verse: Int): String {
            return "verse_screen/$bookId/$chapter/$verse"
        }
    }
}