package com.jpmartineza.tareaprogramacionmultimedia.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jpmartineza.tareaprogramacionmultimedia.cooperFontFamily
import com.jpmartineza.tareaprogramacionmultimedia.data.network.RetrofitClient
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chistako(navController: NavHostController, viewModel: ChistakoViewModel = hiltViewModel()) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Chistaco del dia", fontFamily = cooperFontFamily) },
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
    )
    {
        ChistakoContent(it, navController, viewModel)

    }

}

@Composable
fun ChistakoContent(
    it: PaddingValues,
    navController: NavHostController,
    viewModel: ChistakoViewModel
) {
    val chiste by viewModel.chiste.collectAsState()

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = chiste,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontFamily = cooperFontFamily
        )
        Spacer(modifier = Modifier.height(24.dp))

        BotonGrande(viewModel)

    }
}

    @Composable
    fun BotonGrande(viewModel: ChistakoViewModel){
        Button(
            onClick = {
                viewModel.obtenerChisteAleatorio()
            },
            modifier = Modifier.size(width = 200.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = Color.White),
        ) {
            Text(text = "Dámelo!", fontSize = MaterialTheme.typography.headlineSmall.fontSize, fontFamily = cooperFontFamily)
           }

    }