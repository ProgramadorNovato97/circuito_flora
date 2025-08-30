package org.example.project.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import circuito_flora.composeapp.generated.resources.Res
import circuito_flora.composeapp.generated.resources.cactus
import circuito_flora.composeapp.generated.resources.duraznillo
import circuito_flora.composeapp.generated.resources.falsotomillo
import circuito_flora.composeapp.generated.resources.maihuenia
import circuito_flora.composeapp.generated.resources.ortiga
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

//****************** Ventanas INFO ********************
@Composable
fun infoCactus(onBack: () -> Unit) {

        FloraDetailContent(
            nombre = "Cactus",
            imagen = Res.drawable.cactus,
            descripcion = "Planta columnar con costillas rectas y espinas curvas. Produce flores rosado-amarillentas en forma de campana cerca de la punta, que están cubiertas de lana y cerdas oscuras. El estigma tiene 16 lóbulos morados. Florece a principios del verano, atrayendo a muchos insectos polinizadores, y sus semillas germinan con facilidad. Se encuentra comúnmente bajo otros arbustos.",
            onBack = onBack
        )
}

@Composable
fun infoDuraznillo(onBack: () -> Unit) {
    FloraDetailContent(
        nombre = "Duraznillo",
        imagen = Res.drawable.duraznillo,
        descripcion = "Un arbusto siempre verde y es típico de la estepa patagónica. Tiene hojas grandes verde brillante color botella que si las cortas, sueltan una sustancia blanca llamada látex. Cuando florece, se llena de espigas rojizas que con el tiempo se vuelven amarillas y liberan mucho polen. En la parte de abajo de cada espiga, crecen dos o tres frutos. Al principio, parecen pequeños duraznos, pero cuando maduran se convierten en frutos secos de color marrón, similares a dos avellanas, que se abren para soltar sus grandes semillas. Esta planta es una de las pocas que logra sobrevivir al avance de las dunas de arena.",
        onBack = onBack
    )
}

@Composable
fun infoFalsoTomillo(onBack: () -> Unit) {
    FloraDetailContent(
        nombre = "Falso Tomillo",
        imagen = Res.drawable.falsotomillo,
        descripcion = "Planta baja con ramas extendidas sobre el suelo. A principios de primavera, tiene hojas pequeñas de un color verde grisáceo debido a la sal, que también le da un sabor salado. Sus flores blancas y tenues florecen a fines de enero y principios de febrero, destacándose cuando hay pocas otras plantas en flor. En invierno, su follaje se vuelve ocre y sus ramas grises con puntas afiladas son más visibles.",
        onBack = onBack
    )
}
@Composable
fun infoMaihuenia(onBack: () -> Unit) {
    FloraDetailContent(
        nombre = "Maihuenia",
        imagen = Res.drawable.maihuenia,
        descripcion = "Planta con forma cojines densos que pueden crecer hasta varios metros de ancho. Sus espinas están en grupos de tres y son planas. Las hojas son pequeñas y se agrupan en las ramas jóvenes. Sus flores blancas atraen muchos insectos. Es una planta común en suelos áridos y florece sobre todo en diciembre.",
        onBack = onBack
    )
}
@Composable
fun infoOrtiga(onBack: () -> Unit) {
    FloraDetailContent(
        nombre = "Ortiga",
        imagen = Res.drawable.ortiga,
        descripcion = "Planta que se destaca por sus llamativas flores de color amarillo intenso. A pesar de su nombre, no es una verdadera ortiga y no pica. Sus hojas son ovaladas y a menudo tienen un patrón plateado que las hace muy decorativas. Crece a ras del suelo, formando una densa cubierta vegetal. Prefiere crecer en zonas de sombra o semisombra.",
        onBack = onBack
    )
}


//****************** PLANTILLA INFO ********************
@Composable
fun FloraDetailContent(
    nombre: String,
    imagen: DrawableResource,
    descripcion: String,

    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // 👈 habilita scroll
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = nombre,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(imagen),
            contentDescription = nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Fila de botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { /* TODO */ }) { Text("Botón 1") }
            Button(onClick = { /* TODO */ }) { Text("Botón 2") }
            Button(onClick = onBack) { Text("Volver") }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = descripcion,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )
    }
}





