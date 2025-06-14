package com.example.gastos.roomdb.saldo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "SaldoActual")
data class SaldoActual(
    @PrimaryKey(autoGenerate = true)
    val SaldoId: Int = 0,
    val Saldo: Int = 0,
    val FechaRegistro: Date
)
