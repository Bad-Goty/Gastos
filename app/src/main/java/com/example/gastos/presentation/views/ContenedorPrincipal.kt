package com.example.gastos.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gastos.R
import com.example.gastos.presentation.components.MyCard
import com.example.gastos.presentation.components.MyScaffold
import com.example.gastos.presentation.viewmodel.SaldoActualViewModel
import com.example.gastos.presentation.viewmodel.SaldoActualViewModelFactory
import com.example.gastos.roomdb.saldo.SaldoActualRepository
import com.example.gastos.roomdb.saldo.SaldoDataBase

@Composable
fun ContenedorPrincipal(navController: NavController) {
    Box(Modifier.fillMaxSize()) {
        MyScaffold("Inicio", navController) {
            Column(
                Modifier
                    .fillMaxSize()
                    //.verticalScroll(rememberScrollState())
                    .padding(horizontal = 10.dp)
            ) {

                Datos()

                CardAhorro()

                Spacer(Modifier.height(15.dp))
                GastosRecientes()
            }
        }
    }
}

@Composable
fun Datos() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(top = 10.dp)
    ) {

        Image(
            painter = painterResource(R.drawable.avatar),
            contentDescription = "Imagen",
            modifier = Modifier
                .size(150.dp)
                .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(80.dp))
                .clip(RoundedCornerShape(80.dp)) // 🔹 Recorta la imagen con bordes redondeados
        )

        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Bienvenido:", color = Color.White)
            Text("David Rodriguez", color = Color.White)
        }

    }
}

@Composable
fun CardAhorro() {
    val context = LocalContext.current
    val database = SaldoDataBase.getDatabase(context)
    val repository = SaldoActualRepository(database.saldoActualDao())
    val viewModel: SaldoActualViewModel = viewModel(
        factory = SaldoActualViewModelFactory(repository)
    )

    val saldos by viewModel.allSaldos.collectAsState(initial = emptyList())
    val ultimoSaldo = saldos.firstOrNull()?.Saldo ?: 0

    var showDialog by remember { mutableStateOf(false) }

    MyCard(
        Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Ahorros",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 5.dp)
            )

            Divider(color = MaterialTheme.colorScheme.primary)

            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "$$ultimoSaldo",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    onClick = { showDialog = true },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Edit",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(8.dp) // opcional, para centrar mejor el ícono
                    )
                }
            }
        }
    }

    if (showDialog) {
        EditSaldoDialog(
            currentSaldo = ultimoSaldo,
            onDismiss = { showDialog = false },
            onConfirm = { cantidad ->
                viewModel.agregarSaldo(cantidad)  // ← Usa la nueva función
                showDialog = false
            }
        )
    }
}

@Composable
fun EditSaldoDialog(
    currentSaldo: Int,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var cantidadText by remember { mutableStateOf("") }
    val esInicializar = currentSaldo == 0

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.secondary,
        onDismissRequest = onDismiss,
        title = {
            Text(if (esInicializar) "Saldo Inicial" else "Agregar Dinero")
        },
        text = {
            Column {
                if (esInicializar) {
                    Text("Ingresa tu saldo inicial:")
                } else {
                    Text("Saldo actual: $$currentSaldo")
                    Text("¿Cuánto quieres agregar?")
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = cantidadText,
                    onValueChange = { cantidadText = it },
                    label = { Text(if (esInicializar) "Saldo inicial" else "Cantidad a agregar") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    prefix = { Text("$") }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val cantidad = cantidadText.toIntOrNull() ?: 0
                    if (cantidad > 0) {
                        onConfirm(cantidad)
                    }
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = Color.White
                )

            ) {
                Text(if (esInicializar) "Establecer" else "Agregar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = Color.White
                )
            ) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun GastosRecientes() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "Gastos Recientes",
            color = Color.White,
            fontSize = 25.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .weight(1f) // ← esto limita la altura restante
                .verticalScroll(rememberScrollState())
        ) {
            for (i in 1..3) {
                MyCard(
                    Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                ) {
                    Row(
                        Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier.size(40.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ShoppingCart,
                                    contentDescription = "Gasto",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        Text("Despensa")

                        Text("-$500")
                    }
                }

                Spacer(Modifier.height(10.dp))
            }
        }
    }

}
