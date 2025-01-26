package com.jpmartineza.tareaprogramacionmultimedia

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.jpmartineza.tareaprogramacionmultimedia.data.Anuncios
import com.jpmartineza.tareaprogramacionmultimedia.data.AnunciosDB
import com.jpmartineza.tareaprogramacionmultimedia.data.AnunciosDBDao
import com.jpmartineza.tareaprogramacionmultimedia.data.AnunciosState
import com.jpmartineza.tareaprogramacionmultimedia.data.AnunciosViewModel
import com.jpmartineza.tareaprogramacionmultimedia.data.uRBitDBHelper

import kotlinx.coroutines.launch

class MainActivity() : ComponentActivity() {

    val TAG = "uR_BitApp"
    private lateinit var dbHelper: uRBitDBHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val database = Room.databaseBuilder(this, AnunciosDB::class.java, "AnunciosDB").build()
            val dao = database.anunciosDao()
            val viewModel = AnunciosViewModel(dao)
            val navController = rememberNavController()

            MaquetacionUI(navController = NavHostController(this), viewModel)
        }
        crearBD()

    }

    private fun crearBD() {
        dbHelper = uRBitDBHelper(this)

        dbHelper.InsertarUsuario("admin", "admin", "admin")
        dbHelper.InsertarUsuario("ONG", "ONG", "ONG")
        dbHelper.InsertarUsuario("voluntario", "voluntario", "voluntario")
    }


    //tarea 2, parte 1, registrar cuandoi se restaura la activity
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "activity restaurada")
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }

    companion object


}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MaquetacionUI(navController: NavHostController, viewModel: AnunciosViewModel) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { NavegadorLateral(navController,Modifier.width(Dp(200f))) },
        content = {
            Scaffold(
                topBar = {
                    ToolBar(onMenuClick = {
                        scope.launch {
                            if (drawerState.isOpen) {
                                drawerState.close()
                            } else {
                                drawerState.open()
                            }
                        }
                    })
                },
                //content = {
                // innerPadding -> Box(modifier = Modifier.padding(innerPadding)) {
                content = { Cuerpo(AnunciosState.listadoAnuncios) },
                bottomBar = { BarraInferior() }

            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(onMenuClick: () -> Unit) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }
        },
        title = {
            Text(
                text = "#uR_Bit.",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = cooperFontFamily
            )
        },
        actions =
        {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Account",
                    tint = Color.White
                )
            }


        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.DarkGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 10.dp)
    )
}


@Composable
fun Cuerpo(anuncios: List<Anuncios>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp, 40.dp, 30.dp, 5.dp)
    ) {
        items(anuncios) { anuncio ->
            MuestraAnuncio(anuncios = anuncio)
        }
    }
}


@Preview
@Composable
fun BarraInferior() {
    BottomAppBar(
        containerColor = Color.DarkGray,
        contentColor = Color.White,
        modifier = Modifier.height(100.dp),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "email",
                    tint = Color.White
                )
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Casa",
                    tint = Color.White
                )
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "anadir",
                    tint = Color.White
                )


            }

        }
    )


}

@Composable
fun NavegadorLateral(navController: NavHostController, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .padding(0.dp)
            .background(Color.White, RectangleShape)
    ) {
        Box(
            modifier = Modifier
                .width(250.dp)
                .padding(top = 50.dp, bottom = 10.dp)
                .clip(RoundedCornerShape(9.dp))
                .padding(0.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.brand_logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .align(Alignment.Center)
            )
        }
        ElementosNavLat(icono = Icons.Default.Search, nombre = "Buscador") {
            navController.navigate("BuscarAnuncio")
        }
        ElementosNavLat(icono = Icons.Default.Favorite, nombre = "Favoritos", function = {
            navController.navigate("agregarAnuncio")
        })
        ElementosNavLat(icono = Icons.Default.Create, nombre = "Crear", function = {
            navController.navigate("agregarAnuncio")
        })

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .width(70.dp)
                .clip(RoundedCornerShape(9.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(3.dp)
                    .align(AbsoluteAlignment.TopLeft)
            )
        }


        ElementosNavLat(icono = Icons.Default.Email, nombre = "Email", function = {
            navController.navigate("search")
        })
        ElementosNavLat(icono = Icons.Default.AccountCircle, nombre = "Mi cuenta", function = {
            navController.navigate("search")
        })
        Text(
            text = "Powered by Meº",
            Modifier.padding(start = 130.dp, top = 30.dp),
            color = Color.DarkGray,
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Right
        )
        Spacer(modifier = Modifier.height(20.dp))

    }
}

@Composable
fun NavegadorLateral(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(0.dp)
            .background(Color.White, RectangleShape)
    ) {
        Box(
            modifier = Modifier
                .width(250.dp)
                .padding(top = 50.dp, bottom = 10.dp)
                .clip(RoundedCornerShape(9.dp))
                .padding(0.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.brand_logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .align(Alignment.Center)
            )
        }

        ElementosNavLat(icono = Icons.Default.Search, nombre = "Buscador") {
            navController.navigate("search")
        }
        ElementosNavLat(icono = Icons.Default.Favorite, nombre = "Favoritos") {
            navController.navigate("favorites")
        }
        ElementosNavLat(icono = Icons.Default.Create, nombre = "Crear") {
            navController.navigate("create")
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .width(70.dp)
                .clip(RoundedCornerShape(9.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(3.dp)
                    .align(Alignment.TopStart)
            )
        }
    }
}

@Composable
fun Anuncio(
    foto: Int,
    icono: Int,
    titulo: String,
    texto: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color = Color.LightGray, shape = RoundedCornerShape(9))
            .padding(10.dp)
            .height(100.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id =foto),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(9.dp))
                    .align(Alignment.CenterVertically),
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(3.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = cooperFontFamily
                )
                Text(
                    text = texto,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }
        }
        Icon(
            painter = painterResource(id = icono),
            contentDescription = null,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}


@Composable
fun MuestraAnuncio(anuncios: Anuncios) {
    Anuncio(
        foto = anuncios.imagen,
        icono = R.drawable.baseline_favorite_border_24,
        titulo = anuncios.titulo,
        texto = anuncios.descripcion,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun ElementosNavLat(
    icono: ImageVector,
    nombre: String,
    function: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(4.dp)
    ) {
        Icon(
            imageVector = icono,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .padding(start = 6.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = nombre,
            style = MaterialTheme.typography.titleLarge,
            fontFamily = cooperFontFamily
        )
    }
}

val cooperFontFamily = FontFamily(
    Font(R.font.cooper, FontWeight.Bold)
)

class ViewModelFactory(
    private val dao: AnunciosDBDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnunciosViewModel::class.java)) {
            return super.create(modelClass) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}