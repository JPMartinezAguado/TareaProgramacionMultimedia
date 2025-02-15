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
fun MiCuentaView(navController: NavHostController, viewModel: AnunciosViewModel) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Mi Cuenta", fontFamily = cooperFontFamily) },
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
        MiCuentaContent(it, navController, viewModel)

    }

}


@Composable
fun MiCuentaContent(
    it: PaddingValues,
    navController: NavHostController,
    viewModel: AnunciosViewModel
) {
    Column(
        modifier = Modifier
            .padding(it)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

            Text(
                text = "...not ready yet, mate!!...",
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontFamily = cooperFontFamily
            )
    }
}

