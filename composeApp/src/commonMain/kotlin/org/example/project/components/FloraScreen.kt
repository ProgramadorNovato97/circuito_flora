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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import circuito_flora.composeapp.generated.resources.Res
import circuito_flora.composeapp.generated.resources.catus
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

@Composable
fun FloraScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text( // Titulo de circuito flora
            text = "Flora Caleta Olivia",
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        //lista de imagenes de flora
        val flora = listOf(
            Pair(Res.drawable.catus, "Catus"),
            Pair(Res.drawable.duraznillo, "Duraznillo"),
            Pair(Res.drawable.fabiana, "Fabiana"),
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
            Pair(Res.drawable.unadegato, "Uña de gato"),

            )
        // Grid de imagenes
        LazyVerticalGrid(
            columns = GridCells.Fixed(3)
        ) {
            items(flora) { (imagen, titulo) ->
                SimpleCard(
                    imageRes = imagen,
                    title = titulo
                )
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