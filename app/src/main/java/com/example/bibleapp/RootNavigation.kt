package com.example.bibleapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bibleapp.feature.bible.presentation.BibleScreen
import com.example.bibleapp.feature.bible.presentation.BibleViewModel

@Composable
fun RootNavigation(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            modifier = modifier.padding(innerPadding),
            startDestination = Destination.Home.route
        ) {
            composable(Destination.Home.route){
                val viewModel: BibleViewModel = hiltViewModel()
                val state by viewModel.state.collectAsStateWithLifecycle()

                BibleScreen(
                    state = state,
                    event = viewModel::onEvent
                )
            }
        }
    }
}


sealed class Destination(val route: String) {
    data object Home : Destination("root_nav_graph")
}