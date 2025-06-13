package com.example.gastos.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyCard(
    modifier: Modifier,
    ContentCard: @Composable () -> Unit
    ) {
    Card(
        modifier,

        colors = CardDefaults.cardColors(
             containerColor = MaterialTheme.colorScheme.secondaryContainer
        )

    ) { ContentCard() }
}