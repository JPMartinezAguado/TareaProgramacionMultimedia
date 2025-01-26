package com.jpmartineza.tareaprogramacionmultimedia.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jpmartineza.tareaprogramacionmultimedia.data.Anuncios
import com.jpmartineza.tareaprogramacionmultimedia.data.AnunciosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscarAnuncioView(navController: NavHostController, viewModel: AnunciosViewModel) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Agregar Anuncio") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ){
        BuscarAnuncioContent(it, navController, viewModel)

    }

}


@Composable
fun BuscarAnuncioContent(
    it: PaddingValues,
    navController: NavHostController,
    viewModel: AnunciosViewModel
) {
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var fechaPublicacion by remember { mutableStateOf("") }
    var fechaAccion by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = titulo,
            onValueChange = {titulo = it},
            label = { Text(text = "Titulo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = {descripcion = it},
            label = { Text(text = "Descripcion") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = imagen,
            onValueChange = {imagen = it},
            label = { Text(text = "Imagen") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = tipo,
            onValueChange = {tipo = it},
            label = { Text(text = "Tipo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = ubicacion,
            onValueChange = {ubicacion = it},
            label = { Text(text = "Ubicacion") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = fechaPublicacion,
            onValueChange = {fechaPublicacion = it},
            label = { Text(text = "Fecha Publicacion") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )
        OutlinedTextField(
            value = fechaAccion,
            onValueChange = {fechaAccion = it},
            label = { Text(text = "Fecha Accion") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        Button(
            onClick = {

                val resourceId = context.resources.getIdentifier(imagen, "drawable", context.packageName)
                val anuncio = Anuncios(
                    titulo = titulo,
                    descripcion = descripcion,
                    imagen = resourceId,
                    tipo = tipo,
                    poblacion = ubicacion,
                    fechaPublicacion = fechaPublicacion,
                    fechaAccion = fechaAccion
                )

                viewModel.insertarAnuncio(anuncio)
                navController.popBackStack()
            }
        ) {
            Text(text = "Guardar")
        }

    }

}
