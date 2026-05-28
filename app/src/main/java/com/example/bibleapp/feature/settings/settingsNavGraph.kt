package com.example.bibleapp.feature.settings

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bibleapp.BottomNavDestination

fun NavGraphBuilder.settingsNavGraph(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = BottomNavDestination.Settings.route,
        startDestination = SettingsDestination.SettingsListScreen.route
    ) {
        composable(
            route = SettingsDestination.SettingsListScreen.route
        ) {

        }
    }
}

sealed class SettingsDestination(val route: String){
    data object SettingsListScreen: SettingsDestination("book_mark_list_screen")
}