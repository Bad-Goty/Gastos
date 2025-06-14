package com.example.gastos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gastos.presentation.components.SplashScreen
import com.example.gastos.presentation.ui.theme.GastosTheme
import com.example.gastos.presentation.views.ContenedorGastos
import com.example.gastos.presentation.views.ContenedorMetas
import com.example.gastos.presentation.views.ContenedorPrincipal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GastosTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    composable("splash") { SplashScreen(navController) }
                    composable("home") { ContenedorPrincipal(navController) }
                    composable("gastos") { ContenedorGastos(navController) }
                    composable("metas") { ContenedorMetas(navController) }
                }
            }
        }
    }
}
