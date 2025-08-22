package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

// Imports para el mapa
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.google.accompanist.permissions.*

// Import simple para imágenes
import coil.compose.AsyncImage

// Data class para cada punto de flora
data class PuntoFlora(
    val id: Int,
    val nombre: String,
    val posicion: LatLng,
    val descripcion: String,
    val imagenes: List<String>
)

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapSection() {
    var puntoSeleccionado by remember { mutableStateOf<PuntoFlora?>(null) }
    var ubicacionUsuario by remember { mutableStateOf<LatLng?>(null) }

    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    )

    // Cada marcador
    val puntosFlora = listOf(
        PuntoFlora(
            id = 1,
            nombre = "Flora Punto 1",
            posicion = LatLng(-46.44192812032652, -67.51754269970981),
            descripcion = "Vegetación costera típica de la Patagonia.",
            imagenes = listOf(
                "https://scontent.fcrd3-1.fna.fbcdn.net/v/t39.30808-6/492630904_1354002496201315_1312327471437456498_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=127cfc&_nc_ohc=bit3vQVpdrwQ7kNvwGXwH8v&_nc_oc=Adn69kgBbMkIzn5UN9QGfA4YPVEtWwH6et_BC8_-vbumkMVBy20jWayHZ4klUE3V3Z4Wea7ywAR7Z96nlvnu672W&_nc_zt=23&_nc_ht=scontent.fcrd3-1.fna&_nc_gid=rAzEp1JJUzhpaZluUG1tig&oh=00_AfWTT9FwI4MrQlulBFVWSaku3G_kkVpsKxVONrIfZWGaiQ&oe=68A02BDB",
                "https://scontent.fcrd3-1.fna.fbcdn.net/v/t39.30808-6/491926740_1354002589534639_6550929795724725756_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=127cfc&_nc_ohc=Ae9ibu5k7R0Q7kNvwFOuMxL&_nc_oc=AdnCpl-4NLUxZwYgKOQ_j2RdgwNhWXrbyvuWKX_4mdLsi1CqdKSSIXGr7TEI6VLM144O2N5taWXUo9Xo1fc5CO12&_nc_zt=23&_nc_ht=scontent.fcrd3-1.fna&_nc_gid=sn-U10487m0Y8vjl6zN3xg&oh=00_AfUMwlIMwxZhLnY2qZyvRWwZQW4zWuqtSWbS5Cav1ZThVQ&oe=68A025F8",
                "https://scontent.fcrd3-1.fna.fbcdn.net/v/t39.30808-6/493562187_1354002646201300_2413426691787900256_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=127cfc&_nc_ohc=B-GEmzF8m34Q7kNvwEKfDHR&_nc_oc=AdnmkEj0zkzRox2Bqnk3bQ2ppIlw_szmV69oDDIWPTRHqz7bdgYyR-hFs4QlVho6vWRTiXHfjZjyTf7BF5GLZaH2&_nc_zt=23&_nc_ht=scontent.fcrd3-1.fna&_nc_gid=s3VlN4SxZlcdsFIz8cPMSQ&oh=00_AfW3r5WfISIJZkgbJkxX-8mftl0whKiVb7-BQWVirBjFNw&oe=68A01F24"
            )
        ),
        PuntoFlora(
            id = 2,
            nombre = "Flora Punto 2",
            posicion = LatLng(-46.42242630727814, -67.52422001470067),
            descripcion = "Arbustos adaptados al viento patagónico.",
            imagenes = listOf(
                "https://ejemplo.com/punto2_foto1.jpg",
                "https://ejemplo.com/punto2_foto2.jpg",
                "https://ejemplo.com/punto2_foto3.jpg"
            )
        ),
        PuntoFlora(
            id = 3,
            nombre = "Flora Punto 3",
            posicion = LatLng(-46.45853029395865, -67.5179783621421),
            descripcion = "Especies endémicas de la estepa patagónica.",
            imagenes = listOf(
                "https://scontent.fcrd3-1.fna.fbcdn.net/v/t39.30808-6/492630904_1354002496201315_1312327471437456498_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=127cfc&_nc_ohc=CF9Qbw6s8_sQ7kNvwGCG-Cw&_nc_oc=AdmC__wWCt74xei6LyAKnjTWruk0o19WxC4ZAa9V-dgkbNphhl9S6zb8hdyidHybEkun4BtFLrk789zrj87DJQoj&_nc_zt=23&_nc_ht=scontent.fcrd3-1.fna&_nc_gid=irlBQAGdKTDLyRTgiBWmFg&oh=00_AfWmCyp1veYQ5THnjt-195Lh_ad7RwUAbvtkP9NZ5DEitg&oe=68A733DB",
                "https://scontent.fcrd3-1.fna.fbcdn.net/v/t39.30808-6/491926740_1354002589534639_6550929795724725756_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=127cfc&_nc_ohc=aB_sjb1dlCQQ7kNvwFFiCBF&_nc_oc=Adkb4lVT0RTJVr9QL9H0sVpxXW88_CEjyK7oWbVpbMNSpW6A7zs4xjAI5XlXNShTg0z5VUk83SRMFiBfUysIk6ya&_nc_zt=23&_nc_ht=scontent.fcrd3-1.fna&_nc_gid=BBt5iq-VDr3c165SJOsqsg&oh=00_AfXl3c7LQZ2jm-vI9xoHgPnkcC9X-kSrnksMUbv1JY0_Ow&oe=68A72DF8",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfYnBa1njk7Wpuq2Q0T-kWJi4NTXXK6xITwsfzyZOuZBjJSddWzavB8OQ2FS5g8XIj5us&usqp=CAU"
            )
        )
    )

    // Funciones para manejar los clicks de los marcadores
    fun onMarkerFloraClick(punto: PuntoFlora) {
        puntoSeleccionado = punto
    }

    fun cerrarDetalleFlora() {
        puntoSeleccionado = null
    }

    Column {


        // Card de información cuando se selecciona un marcador
        puntoSeleccionado?.let { punto ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = punto.nombre,
                        style = MaterialTheme.typography.titleMedium
                    )

                    CarouselSimple(punto.imagenes)

                    Text(
                        text = punto.descripcion,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Button(
                        onClick = { cerrarDetalleFlora() },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Cerrar")
                    }
                }
            }
        }

        // Botón para solicitar permisos de ubicación
        if (!locationPermissions.allPermissionsGranted) {
            Button(
                onClick = { locationPermissions.launchMultiplePermissionRequest() },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Activar ubicación actual")
            }
        }

        // Configuración de la cámara - inicia en coordenada por defecto
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(
                LatLng(-46.44192812032652, -67.51754269970981), // Coordenada por defecto (Caleta Olivia)
                14f // Más zoom para ver mejor los detalles
            )
        }

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = locationPermissions.allPermissionsGranted,
                mapType = MapType.SATELLITE
            ),
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = locationPermissions.allPermissionsGranted,
                zoomControlsEnabled = true
            )
        ) {
            // Marcadores de flora - SÚPER FÁCIL ahora!
            puntosFlora.forEach { punto ->
                Marker(
                    state = MarkerState(position = punto.posicion),
                    title = punto.nombre,
                    snippet = "Circuito de flora local",
                    onClick = {
                        onMarkerFloraClick(punto)
                        true
                    }
                )
            }
        }
    }
}

@Composable
fun CarouselSimple(urls: List<String>) {
    var imagenActual by remember { mutableStateOf(0) }

    fun siguienteImagen() {
        imagenActual = (imagenActual + 1) % urls.size
    }

    fun imagenAnterior() {
        imagenActual = if (imagenActual > 0) imagenActual - 1 else urls.size - 1
    }

    Column {
        AsyncImage(
            model = urls[imagenActual],
            contentDescription = "Flora imagen",
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable { siguienteImagen() },
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            /*Button(onClick = { imagenAnterior() }) {
                Text("◀")
            }*/

            Text(
                text = "${imagenActual + 1} / ${urls.size}",
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            /*Button(onClick = { siguienteImagen() }) {
                Text("▶")
            }*/
        }
    }
}