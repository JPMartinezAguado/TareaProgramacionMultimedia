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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val TAG = "uR_BitApp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaquetacionUI()
        }

    }

    //tarea 2, parte 1, registrar cuandoi se restaura la activity
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "activity restaurada")
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable

fun MaquetacionUI() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { NavegadorLateral(Modifier.width(Dp(200f))) },
        content = {
            Scaffold(
                topBar = { ToolBar(onMenuClick = {
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
                content = {Cuerpo()},
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
            IconButton(onClick = {}){
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

@Preview
@Composable
fun Cuerpo() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp, 40.dp, 30.dp, 5.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(60.dp))
            repeat(14) { // Para evitar repetición manual
                MuestraAnuncio()
            }
        }
    }
}

@Preview
@Composable
fun  BarraInferior() {
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
fun NavegadorLateral(modifier: Modifier = Modifier)
{

    Column(
        modifier = Modifier
            .padding(0.dp)
            .background(Color.White, RectangleShape)
    ){
        Box(
            modifier = Modifier
                .width(250.dp)
                .padding(top = 50.dp, bottom = 10.dp)
                .clip(RoundedCornerShape(9.dp))
                .padding(0.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.brand_logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .align(Alignment.Center)
            )
        }
        ElementosNavLat(icono = Icons.Default.Search, nombre = "Buscador")
        ElementosNavLat(icono = Icons.Default.Favorite, nombre = "Favoritos")
        ElementosNavLat(icono = Icons.Default.Create, nombre = "Crear")

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .width(70.dp)
                .clip(RoundedCornerShape(9.dp))
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(3.dp)
                    .align(AbsoluteAlignment.TopLeft)
            )
        }


        ElementosNavLat(icono = Icons.Default.Email, nombre = "Email")
        ElementosNavLat(icono = Icons.Default.AccountCircle, nombre = "Mi cuenta")
        Text(
            text = "Powered by Meº",
            Modifier.padding(start = 130.dp, top =30.dp),
            color = Color.DarkGray,
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Right
        )
        Spacer(modifier = Modifier.height(20.dp))

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
                painter = painterResource(id = foto),
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

@Preview(showBackground = true)
@Composable
fun MuestraAnuncio() {
    Anuncio(
        foto = R.drawable.googlemaps ,
        icono = R.drawable.baseline_favorite_border_24,
        titulo = "TITULO DEL ANUNCIO",
        texto = "Aquí va un texto de prueba. Dispone de scroll, por lo que puede contener varias lineas mas de las que se muestran." +
                "Sin embargo, solo ira una pequeña descripcion y el resto de detalles se verán al abrir el anuncio",
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun ElementosNavLat(
    icono : ImageVector,
    nombre: String
){
    Row (
        modifier = Modifier
            .padding(4.dp)
    ){
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
    Font(R.font.cooper, FontWeight.Bold))