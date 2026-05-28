package com.example.bibleapp.feature.books.presentation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bibleapp.Destination

fun NavGraphBuilder.bibleBookNavGraph(
    navController: NavController,
) {
    navigation(
        route = Destination.BibleBookNavGraph.route,
        startDestination = BibleBookDestination.BibleBookScreen.route
    ){
        composable(BibleBookDestination.BibleBookScreen.route){
            val viewModel: BibleBooksViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            BibleBookScreen(
                state = state,
                event = viewModel::onEvent
            )
        }
    }
}

sealed class BibleBookDestination(val route: String){
    data object BibleBookScreen: BibleBookDestination("bible_book_screen")
}