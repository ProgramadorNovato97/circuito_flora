package org.example.project

import FavoritosScreen
import org.example.project.components.FloraScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    var selectedTab by remember { mutableStateOf(0) }
    val items = listOf(
        NavItem("Flora", Icons.Filled.Menu),
        NavItem("Mapa", Icons.Filled.LocationOn),
        NavItem("Favoritos", Icons.Filled.Favorite),
    )

    MaterialTheme {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = null) },
                            label = { Text(item.title) },
                            selected = selectedTab == index,
                            onClick = { selectedTab = index }
                        )
                    }
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (selectedTab == 0) {
                    FloraScreen()
                }
                if (selectedTab == 1) {
                    MapSection()
                }
                if (selectedTab == 2) {
                    FavoritosScreen()
                }
            }
        }
    }
}

data class NavItem(val title: String, val icon: ImageVector)



