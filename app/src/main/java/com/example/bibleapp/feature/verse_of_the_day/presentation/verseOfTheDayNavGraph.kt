package com.example.bibleapp.feature.verse_of_the_day.presentation

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bibleapp.BottomNavDestination

fun NavGraphBuilder.verseOfTheDayNavGraph(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = BottomNavDestination.VerseOfTheDay.route,
        startDestination = VerseOfTheDayDestination.VerseOfTheDayScreen.route
    ) {
        composable(
            route = VerseOfTheDayDestination.VerseOfTheDayScreen.route
        ) {
            val viewModel: VerseOfTheDayViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            VerseOfTheDayScreen(
                state = state,
                event = viewModel::onEvent
            )
        }
    }
}

sealed class VerseOfTheDayDestination(val route: String){
    data object VerseOfTheDayScreen: VerseOfTheDayDestination("verse_of_the_day_screen")
}