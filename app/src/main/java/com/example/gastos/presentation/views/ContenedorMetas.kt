package com.example.gastos.presentation.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gastos.presentation.components.MyScaffold

@Composable
fun ContenedorMetas(navController: NavController) {

    BackHandler {
        navController.navigate("home")
        {
            popUpTo(0) { inclusive = true } // Limpia el back stack
            launchSingleTop = true
        }
    }

    Box(Modifier.fillMaxSize()){
        MyScaffold(
            Title = "Metas",
            navController
        ) {
            Column { Text("Hola") }
        }
    }
}