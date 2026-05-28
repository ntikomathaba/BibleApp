package com.example.bibleapp

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BibleBottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val bottomNavItems = listOf(
        BottomNavDestination.Bible,
        BottomNavDestination.BookMark,
        BottomNavDestination.Settings
    )

    NavigationBar {
        val currentDestination =
            navController.currentBackStackEntryAsState()
                .value
                ?.destination

        bottomNavItems.forEach { item ->
            val isSelected =
                currentDestination?.hierarchy?.any {
                    it.route == item.route
                } == true

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "Icon"
                    )
                },
                label = {
                    Text(item.title)
                }
            )
        }
    }
}