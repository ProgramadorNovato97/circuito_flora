package org.example.project

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image



import androidx.compose.ui.layout.ContentScale
import circuito_flora.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


import circuito_flora.composeapp.generated.resources.tuna
import circuito_flora.composeapp.generated.resources.unadegato
import circuito_flora.composeapp.generated.resources.fabiana

@Composable
@Preview
fun App() {
    var selectedTab by remember { mutableStateOf(0) }

    MaterialTheme {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    val items = listOf(
                        NavItem("Flora", Icons.Default.Menu),
                        NavItem("Mapa", Icons.Default.LocationOn),
                        NavItem("Favoritos", Icons.Default.Favorite),
                    )
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
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                when (selectedTab) {
                    0 -> FloraScreen()
                    1 -> MapSection()
                    2 -> FavoritosScreen()
                }
            }
        }
    }
}
data class NavItem(val title: String, val icon: ImageVector)


@Composable
fun FavoritosScreen() {
    Text("Pantalla Favoritos")
}




@Composable
fun FloraScreen() {
    val flora = listOf(
        Pair(Res.drawable.tuna, "Tuna espin"),
        Pair(Res.drawable.unadegato, "Uña de gato"),
        Pair(Res.drawable.fabiana, "Fabiana"),
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3)
    ) {
        items(flora) { (imagen, titulo) ->
            SimpleCard(
                imageRes = imagen,
                title = titulo
            )
        }


        //*****Repetidos******
        items(flora) { (imagen, titulo) ->
            SimpleCard(
                imageRes = imagen,
                title = titulo
            )
        }


        items(flora) { (imagen, titulo) ->
            SimpleCard(
                imageRes = imagen,
                title = titulo
            )
        }

        items(flora) { (imagen, titulo) ->
            SimpleCard(
                imageRes = imagen,
                title = titulo
            )
        }

        //***********


    }










}

@Composable
fun SimpleCard(
    imageRes: DrawableResource,
    title: String,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = Modifier.padding(6.dp)
    ) {
        Column {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}