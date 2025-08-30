package org.example.project.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import circuito_flora.composeapp.generated.resources.Res
import circuito_flora.composeapp.generated.resources.cactus
import circuito_flora.composeapp.generated.resources.fabiana
import circuito_flora.composeapp.generated.resources.maihuenia
import circuito_flora.composeapp.generated.resources.ortiga
import circuito_flora.composeapp.generated.resources.sulupe
import circuito_flora.composeapp.generated.resources.tomillo
import circuito_flora.composeapp.generated.resources.tuna
import circuito_flora.composeapp.generated.resources.unadegato
import circuito_flora.composeapp.generated.resources.falsotomillo
import circuito_flora.composeapp.generated.resources.duraznillo
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource



import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


@Composable
fun FloraScreen() {
    var currentScreen by remember { mutableStateOf<(@Composable () -> Unit)?>(null) }

    if (currentScreen != null) {
        currentScreen!!()
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Flora Caleta Olivia",
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            val flora: List<Triple<DrawableResource, String, @Composable () -> Unit>> = listOf(
                Triple(Res.drawable.cactus, "Cactus") { infoCactus(onBack = { currentScreen = null }) },
                Triple(Res.drawable.duraznillo, "Duraznillo") { infoDuraznillo(onBack = { currentScreen = null }) },
                Triple(Res.drawable.falsotomillo, "Falso Tomillo") { infoFalsoTomillo(onBack = { currentScreen = null }) },
                Triple(Res.drawable.maihuenia, "Maihuenia") { infoMaihuenia(onBack = { currentScreen = null }) },
                Triple(Res.drawable.ortiga, "Ortiga") { infoOrtiga(onBack = { currentScreen = null }) },

                )


            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(flora) { (imagen, titulo, destino) ->
                    SimpleCard(
                        imageRes = imagen,
                        title = titulo,
                        onClick = { currentScreen = destino }
                    )
                }
            }
        }
    }
}










// Formato Card
@Composable
fun SimpleCard(
    imageRes: DrawableResource,
    title: String,
    onClick: () -> Unit = {}
) {
    Card(onClick = onClick, modifier = Modifier.padding(6.dp)) {
        Column {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}












/*

Pair(Res.drawable.falsotomillo, "Falso Tomillo"),
Pair(Res.drawable.maihuenia, "Maihuenia"),
Pair(Res.drawable.ortiga, "Ortiga"),
Pair(Res.drawable.sulupe, "Sulupe"),
Pair(Res.drawable.tomillo, "Tomillo"),
Pair(Res.drawable.tuna, "Tuna verde"),
Pair(Res.drawable.unadegato, "Uña de gato"),
Pair(Res.drawable.catus, "Catus"),
Pair(Res.drawable.duraznillo, "Duraznillo"),
Pair(Res.drawable.fabiana, "Fabiana"),
Pair(Res.drawable.falsotomillo, "Falso Tomillo"),
Pair(Res.drawable.maihuenia, "Maihuenia"),
Pair(Res.drawable.ortiga, "Ortiga"),
Pair(Res.drawable.sulupe, "Sulupe"),
Pair(Res.drawable.tomillo, "Tomillo"),
Pair(Res.drawable.tuna, "Tuna verde"),
Pair(Res.drawable.unadegato, "Uña de gato"), */