package com.example.bibleapp.feature.bookMarks

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bibleapp.BottomNavDestination

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

        }
    }
}

sealed class BookMarkDestination(val route: String){
    data object BookMarkListScreen: BookMarkDestination("book_mark_list_screen")
}