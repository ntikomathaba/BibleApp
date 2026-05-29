package com.example.bibleapp.feature.bible_search.presentation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bibleapp.Destination

fun NavGraphBuilder.bibleNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = BibleDestination.Home.route,
        route = Destination.BibleNavGraph.route
    ){
        composable(BibleDestination.Home.route){
            val viewModel: BibleViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            BibleScreen(
                state = state,
                event = viewModel::onEvent
            )
        }
    }
}

sealed class BibleDestination(val route: String){
    data object Home: BibleDestination("bible_home")
}