package com.jpmartineza.tareaprogramacionmultimedia.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jpmartineza.tareaprogramacionmultimedia.MaquetacionUI
import com.jpmartineza.tareaprogramacionmultimedia.data.network.RetrofitClient
import com.jpmartineza.tareaprogramacionmultimedia.ui.AnunciosViewModel
import com.jpmartineza.tareaprogramacionmultimedia.ui.AgregarAnuncioView
import com.jpmartineza.tareaprogramacionmultimedia.ui.BuscarAnuncioView
import com.jpmartineza.tareaprogramacionmultimedia.ui.Chistako
import com.jpmartineza.tareaprogramacionmultimedia.ui.ChistakoViewModel
import com.jpmartineza.tareaprogramacionmultimedia.ui.ChistakoViewModel_Factory


@Composable
fun NavManager(viewModel : AnunciosViewModel){
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") {
            MaquetacionUI(navController, viewModel)
        }

        composable("agregarAnuncio") {
            AgregarAnuncioView(navController, viewModel)
        }

        composable("BuscarAnuncio") {

            BuscarAnuncioView(
                navController,
                viewModel)
        }

        composable("contarChiste") {
            Chistako(navController, ChistakoViewModel(RetrofitClient()))
        }


    }
}


