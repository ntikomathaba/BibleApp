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
import com.example.bibleapp.feature.bible.presentation.bibleNavGraph
import com.example.bibleapp.feature.books.presentation.bibleBookNavGraph

@Composable
fun RootNavigation(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            modifier = modifier.padding(innerPadding),
            startDestination = Destination.BibleBookNavGraph.route
        ) {
            bibleNavGraph(
                navController = navController
            )

            bibleBookNavGraph(
                navController = navController
            )
        }
    }
}


sealed class Destination(val route: String) {
    data object BibleNavGraph : Destination("bible_nav_graph")
    data object BibleBookNavGraph : Destination("bible_book_nav_graph")
}