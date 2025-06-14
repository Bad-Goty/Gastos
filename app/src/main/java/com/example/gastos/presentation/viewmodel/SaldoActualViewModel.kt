package com.example.gastos.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gastos.roomdb.saldo.SaldoActual
import com.example.gastos.roomdb.saldo.SaldoActualRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date

class SaldoActualViewModel(private val repository: SaldoActualRepository) : ViewModel() {

    val allSaldos: Flow<List<SaldoActual>> = repository.getAllSaldos()

    fun insertSaldo(saldo: Int) {
        viewModelScope.launch {
            val nuevoSaldo = SaldoActual(
                Saldo = saldo,
                FechaRegistro = Date()
            )
            repository.insertSaldo(nuevoSaldo)
        }
    }

    fun updateSaldo(saldoActual: SaldoActual) {
        viewModelScope.launch {
            repository.updateSaldo(saldoActual)
        }
    }

    fun deleteSaldo(saldoActual: SaldoActual) {
        viewModelScope.launch {
            repository.deleteSaldo(saldoActual)
        }
    }

    // En SaldoActualViewModel.kt
    fun agregarSaldo(cantidad: Int) {
        viewModelScope.launch {
            val ultimoSaldo = repository.getUltimoSaldo()

            if (ultimoSaldo == null || ultimoSaldo.Saldo == 0) {
                // Primera vez o saldo en 0 - crear saldo inicial
                val nuevoSaldo = SaldoActual(
                    Saldo = cantidad,
                    FechaRegistro = Date()
                )
                repository.insertSaldo(nuevoSaldo)
            } else {
                // Ya existe saldo - SUMAR la nueva cantidad
                val saldoActualizado = ultimoSaldo.copy(
                    Saldo = ultimoSaldo.Saldo + cantidad,  // ← SUMA aquí
                    FechaRegistro = Date()
                )
                repository.updateSaldo(saldoActualizado)
            }
        }
    }
}

class SaldoActualViewModelFactory(private val repository: SaldoActualRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SaldoActualViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SaldoActualViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

