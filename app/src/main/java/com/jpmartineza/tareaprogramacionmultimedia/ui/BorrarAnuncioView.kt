package com.jpmartineza.tareaprogramacionmultimedia.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jpmartineza.tareaprogramacionmultimedia.cooperFontFamily
import com.jpmartineza.tareaprogramacionmultimedia.data.room.Anuncios

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BorrarAnuncioView(navController: NavHostController, viewModel: AnunciosViewModel) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Eliminacion de Anuncio",  fontFamily = cooperFontFamily) },
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
        BorrarAnuncioContent(it, navController, viewModel)

    }

}


@Composable
fun BorrarAnuncioContent(
    it: PaddingValues,
    navController: NavHostController,
    viewModel: AnunciosViewModel
) {
//    var titulo by remember { mutableStateOf("") }
//    var descripcion by remember { mutableStateOf("") }
//    var imagen by remember { mutableStateOf("") }
//    var tipo by remember { mutableStateOf("") }
//    var ubicacion by remember { mutableStateOf("") }
//    var fechaPublicacion by remember { mutableStateOf("") }
//    var fechaAccion by remember { mutableStateOf("") }
//
//    val context = LocalContext.current

    val anuncios by viewModel.anuncios.observeAsState(emptyList())
    var anuncioElegido by remember { mutableStateOf<Anuncios?>(null) }
    var extendido by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            OutlinedTextField(
                value = anuncioElegido?.titulo ?: "",
                onValueChange = {},
                label = { Text(text = "Titulo") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { extendido = !extendido }) {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                }
            )
            DropdownMenu(
                expanded = extendido,
                onDismissRequest = { extendido = false }
            ) {
                anuncios.forEach { anuncio ->
                    DropdownMenuItem(onClick = {
                        anuncioElegido = anuncio
                        extendido = false
                    },
                        text = {
                            Text(text = anuncio.titulo)
                        }
                    )
                }

            }

        }
        Spacer(modifier = Modifier.padding(20.dp))

        Button(
            onClick = {
                anuncioElegido?.let {
                    viewModel.eliminarAnuncio(it)
                    navController.popBackStack()
                }
            },modifier = Modifier.size(width = 200.dp, height = 50.dp),
             colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = Color.White),
        ) {
            Text(
                text = "Eliminar",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontFamily = cooperFontFamily
            )
        }
    }


}


//        OutlinedTextField(
//            value = tipo,
//            onValueChange = {tipo = it},
//            label = { Text(text = "Tipo") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 30.dp)
//                .padding(bottom = 15.dp)
//        )
//
//        OutlinedTextField(
//            value = ubicacion,
//            onValueChange = {ubicacion = it},
//            label = { Text(text = "Ubicacion") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 30.dp)
//                .padding(bottom = 15.dp)
//        )
//
//
//        Button(
//            onClick = {
//
//                val resourceId = context.resources.getIdentifier(imagen, "drawable", context.packageName)
//                val anuncio = Anuncios(
//                    titulo = titulo,
//                    descripcion = descripcion,
//                    imagen = resourceId,
//                    tipo = tipo,
//                    poblacion = ubicacion,
//                    fechaPublicacion = fechaPublicacion,
//                    fechaAccion = fechaAccion
//                )
//
//                viewModel.eliminarAnuncio(anuncio)
//                navController.popBackStack()
//            }
//        ) {
//            Text(text = "Guardar")
//        }
//
//    }




