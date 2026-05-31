package com.example.bibleapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CalendarViewDay
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.bibleapp.feature.bible_search.presentation.bibleSearchNavGraph
import com.example.bibleapp.feature.bookMarks.bookMarksNavGraph
import com.example.bibleapp.feature.books.presentation.bibleBookNavGraph
import com.example.bibleapp.feature.settings.settingsNavGraph
import com.example.bibleapp.feature.verse_of_the_day.presentation.verseOfTheDayNavGraph

@Composable
fun RootNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BibleBottomNavigation(
                navController = navController,
            )
        }
    ) { innerPadding ->
        val contentPadding = PaddingValues(
            start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
            top = 0.dp,
            end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
            bottom = 32.dp
        )

        NavHost(
            navController = navController,
            modifier = modifier.padding(contentPadding),
            startDestination = BottomNavDestination.Bible.route
        ) {
            bibleSearchNavGraph(
                navController = navController
            )

            bibleBookNavGraph(
                navController = navController,
                modifier = modifier.padding(contentPadding),
                onSearchClicked = {
                    navController.navigate(
                        route = Destination
                            .BibleSearchNavGraph
                            .route
                    ) {
                        popUpTo(Destination.BibleNavGraph.route) {
                            inclusive = true
                        }
                    }
                }
            )

            bookMarksNavGraph(
                navController = navController,
                modifier = modifier.padding(contentPadding)
            )

            verseOfTheDayNavGraph(
                navController = navController,
                modifier = modifier
            )

            settingsNavGraph(
                navController = navController,
                modifier = modifier
            )

        }
    }
}


sealed class Destination(val route: String) {
    data object BibleNavGraph : Destination("bible_nav_graph")
    data object BibleSearchNavGraph : Destination("search_nav_graph")
}

sealed class BottomNavDestination(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Bible : BottomNavDestination(
        route = "bible",
        title = "Bible",
        icon = Icons.AutoMirrored.Filled.MenuBook
    )

    data object BookMark : BottomNavDestination(
        route = "saved",
        title = "Book Marks",
        icon = Icons.Default.Bookmark
    )

    data object VerseOfTheDay : BottomNavDestination(
        route = "verse_of_the_day",
        title = "Verse of The Day",
        icon = Icons.Default.CalendarViewDay
    )

    data object Settings : BottomNavDestination(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}