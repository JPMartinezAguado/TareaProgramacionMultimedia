package com.jpmartineza.tareaprogramacionmultimedia

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jpmartineza.tareaprogramacionmultimedia.data.room.Anuncios
import com.jpmartineza.tareaprogramacionmultimedia.data.sqlite.uRBitDBHelper
import com.jpmartineza.tareaprogramacionmultimedia.navegacion.NavManager
import com.jpmartineza.tareaprogramacionmultimedia.ui.AnunciosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {


    private lateinit var dbHelper: uRBitDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val anunciosViewModel: AnunciosViewModel = hiltViewModel()
            NavManager(anunciosViewModel)
        }
        crearBD()
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val database = Room.databaseBuilder(applicationContext, AnunciosDB::class.java, "AnunciosDB").build()
//            val dao = database.anunciosDao()
//            val viewModel = AnunciosViewModel(
//                getAnuncios = GetAnunciosUserCase(AnunciossRepositorio()),
//                addAnuncio = AddAnuncioUserCase(AnunciossRepositorio()),
//                removeAnuncio = RemoveAnuncioUserCase(AnunciossRepositorio()),
//                dao = dao)
//
//            withContext(Dispatchers.Main) {
//                setContent {
//                    //val navController = rememberNavController()
//                    NavManager(viewModel = hiltViewModel())
//                }
//            }
//        }
//        crearBD()


    private fun crearBD() {
        dbHelper = uRBitDBHelper(this)

        dbHelper.InsertarUsuario("admin", "admin", "admin")
        dbHelper.InsertarUsuario("ONG", "ONG", "ONG")
        dbHelper.InsertarUsuario("voluntario", "voluntario", "voluntario")
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MaquetacionUI( navController: NavHostController, viewModel: AnunciosViewModel) {



    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent= {
            Box(
                modifier = Modifier
                   .fillMaxWidth(0.7f)
                    .fillMaxHeight()
                   .padding(bottom = 80.dp)
                   .background(Color.White)
        ){
                NavegadorLateral(navController)
            }
        },
        content = {
            Scaffold(
                topBar = {
                    ToolBar(navController, onMenuClick = {
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
                content = { Cuerpo(anuncios = viewModel.anunciosState.map { it.anuncios }) },
                bottomBar = { BarraInferior(navController) }
            )
        },
        //modifier = Modifier.width(if (drawerState.isOpen) 1.dp else 0.dp)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(navController: NavHostController, onMenuClick: () -> Unit) {
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
            IconButton(onClick = { navController.navigate("MiCuenta") }) {
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
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
    )
}


@Composable
fun Cuerpo(anuncios: Flow<List<Anuncios>>) {

    val anunciosList by anuncios.collectAsState(initial = emptyList())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp, 65.dp, 30.dp, 80.dp)
    ) {
        items(anunciosList) { anuncio ->
            MuestraAnuncio(anuncios = anuncio)
        }
    }
}



@Composable
fun BarraInferior(navController: NavHostController) {
    BottomAppBar(
        containerColor = Color.DarkGray,
        contentColor = Color.White,
        modifier = Modifier.height(80.dp),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate("Inicio")
                }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refrescar",
                        tint = Color.White
                    )
                }
                IconButton(onClick = {
                    navController.navigate("Inicio")
                }) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = Color.White
                    )
                }
                IconButton(onClick = {
                    navController.navigate("agregarAnuncio")
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Añadir",
                        tint = Color.White
                    )
                }
            }
        }
    )
}


@Composable
fun NavegadorLateral (navController: NavHostController, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            //.padding(end = 60.dp,bottom = 80.dp)
            .background(Color.White, RectangleShape)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
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
        ElementosNavLat(icono = Icons.Default.Delete, nombre = "Eliminar") {
            navController.navigate("BorrarAnuncio")
        }
        ElementosNavLat(icono = Icons.Default.Create, nombre = "Crear") {
            navController.navigate("agregarAnuncio")
        }

        //ElementosNavLat(icono = Icons.Default.Favorite, nombre = "Crear") {
        //    navController.navigate("agregarAnuncio")
        //}
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


        ElementosNavLat(
            icono = Icons.Default.Email, nombre = "Chistako") {
            navController.navigate("contarChiste")

        }
        ElementosNavLat(icono = Icons.Default.AccountCircle, nombre = "Mi cuenta"){
            navController.navigate("MiCuenta")
        }
        Text(
            text = "Powered by Meº",
            Modifier.padding(start = 80.dp, top = 30.dp, bottom = 10.dp),
            color = Color.DarkGray,
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Right
        )
        //Spacer(modifier = Modifier.height(20.dp))

    }
}

//@Composable
//fun ChisteDelDia(viewModel: ChistakoViewModel = ChistakoViewModel()) {
//    Column(modifier = Modifier.padding(16.dp)){
//        Text("Chiste del día", style = MaterialTheme.typography.headlineMedium)
//        Text(chiste.texto, style = MaterialTheme.typography.bodyMedium)
//    }
//
//}


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
    onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onClick() },
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
