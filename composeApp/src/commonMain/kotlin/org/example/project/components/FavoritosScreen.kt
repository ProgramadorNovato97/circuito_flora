package org.example.project

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Imports para el mapa
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.google.accompanist.permissions.*
import com.google.android.gms.maps.CameraUpdateFactory

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FavoritosScreen() {
    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(-46.44192812032652, -67.51754269970981), // Coordenada por defecto
            14f
        )
    }

    Column {
        // Botón para solicitar permisos de ubicación
        if (!locationPermissions.allPermissionsGranted) {
            Button(
                onClick = { locationPermissions.launchMultiplePermissionRequest() },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Activar ubicación actual")
            }
        }

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
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
            // Marker simple
            Marker(
                state = MarkerState(position = LatLng(-46.44192812032652, -67.51754269970981)),
                title = "El gorosito",
                snippet = "Monumento al obrero petrolero"
            )
        }
    }
}