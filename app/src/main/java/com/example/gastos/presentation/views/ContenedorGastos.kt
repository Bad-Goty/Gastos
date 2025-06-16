package com.example.gastos.presentation.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gastos.presentation.components.MyCard
import com.example.gastos.presentation.components.MyScaffold
import com.example.gastos.presentation.viewmodel.SaldoActualViewModel
import com.example.gastos.presentation.viewmodel.SaldoActualViewModelFactory
import com.example.gastos.roomdb.saldo.SaldoActualRepository
import com.example.gastos.roomdb.saldo.SaldoDataBase

@Composable
fun ContenedorGastos(navController: NavController) {
    BackHandler {
        navController.navigate("home")
        {
            popUpTo(0) { inclusive = true } // Limpia el back stack
            launchSingleTop = true
        }
    }

    Box(Modifier.fillMaxSize()) {
        MyScaffold(
            Title = "Gastos",
            navController
        ) {
            Column(
                Modifier.padding(10.dp)
            ) {
                CardAhorroGasto()

                Filtros()
            }
        }
    }
}

@Composable
fun CardAhorroGasto() {
    val context = LocalContext.current
    val database = SaldoDataBase.getDatabase(context)
    val repository = SaldoActualRepository(database.saldoActualDao())
    val viewModel: SaldoActualViewModel = viewModel(
        factory = SaldoActualViewModelFactory(repository)
    )

    val saldos by viewModel.allSaldos.collectAsState(initial = emptyList())
    val ultimoSaldo = saldos.firstOrNull()?.Saldo ?: 0

    //var showDialog by remember { mutableStateOf( false ) }

    MyCard(
        Modifier
            .fillMaxWidth()
            .height(140.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Saldo a favor",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 5.dp)
            )

            Divider(color = MaterialTheme.colorScheme.primary)

            Text(
                "$$ultimoSaldo",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 5.dp)
            )

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp), onClick = {}) {
                Text("Agregar gasto", color = Color.White, fontWeight = FontWeight.ExtraBold)
            }
        }
    }
}

@Composable
fun Filtros() {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = {}
        ) { Text("Dia", color = Color.White) }

        Button(
            onClick = {}
        ) { Text("Semana", color = Color.White) }

        Button(
            onClick = {}
        ) { Text("Mes", color = Color.White) }

        Button(
            onClick = {}
        ) { Text("Año", color = Color.White) }
    }

}