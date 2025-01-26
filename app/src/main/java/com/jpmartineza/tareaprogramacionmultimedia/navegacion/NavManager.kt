package com.jpmartineza.tareaprogramacionmultimedia.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jpmartineza.tareaprogramacionmultimedia.MainActivity
import com.jpmartineza.tareaprogramacionmultimedia.data.AnunciosViewModel
import com.jpmartineza.tareaprogramacionmultimedia.ui.AgregarAnuncioView
import com.jpmartineza.tareaprogramacionmultimedia.ui.BuscarAnuncioView


@Composable
fun NavManager(viewModel : AnunciosViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") {
            MainActivity()
        }

        composable("agregarAnuncio") {
            AgregarAnuncioView(navController, viewModel)
        }

        composable("BuscarAnuncio/") {

            BuscarAnuncioView(
                navController,
                viewModel)
        }


    }
}


