package com.example.gastos.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold(
    Title: String,
    navController: NavController,
    Content: @Composable () -> Unit,

) {
    //Accede de forma segura a la ruta (String) de esa entrada. Si por alguna razón no hay nada (por ejemplo, justo al arrancar), evita un crash con ?.
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        Title,
                        Modifier.fillMaxWidth(),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    titleContentColor = Color.White
                )
            )
        },

        bottomBar = {
            BottomAppBar(containerColor = MaterialTheme.colorScheme.secondary) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column {
                        IconButton(
                            onClick = {
                                if (currentRoute != "home") {
                                    navController.navigate("home") {
                                        popUpTo(0) { inclusive = true } // Limpia todo el back stack
                                        launchSingleTop = true
                                    }
                                }
                            }
                        ) {

                            Icon(
                                Icons.Default.Home,
                                contentDescription = "Home",
                                tint = Color.White
                            )

                        }
                        Text("Home", color = Color.White)
                    }


                    Column {
                        IconButton(
                            onClick = {
                                if (currentRoute != "gastos") {
                                    navController.navigate("gastos") {
                                        launchSingleTop = true
                                    }
                                }
                            }
                        ) {

                            Icon(
                                Icons.Default.MonetizationOn,
                                contentDescription = "Gastos",
                                tint = Color.White
                            )

                        }
                        Text("Gastos", color = Color.White)
                    }

                    Column {
                        IconButton(
                            onClick = {
                                if (currentRoute != "metas") {
                                    navController.navigate("metas") {
                                        launchSingleTop = true
                                    }
                                }
                            }
                        ) {

                            Icon(
                                Icons.Filled.EmojiEvents,
                                contentDescription = "Metas",
                                tint = Color.White,
                                /*modifier = Modifier
                                    .size(25.dp)
                                    .clickable {
                                        showDialog = true
                                    }*/
                            )

                        }
                        Text("Metas", color = Color.White)
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Content()
        }
    }
}