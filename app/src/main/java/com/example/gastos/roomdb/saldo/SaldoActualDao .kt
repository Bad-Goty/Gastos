package com.example.gastos.roomdb.saldo

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SaldoActualDao {
    @Query("SELECT * FROM SaldoActual ORDER BY FechaRegistro DESC")
    fun getAllSaldos(): Flow<List<SaldoActual>>

    @Query("SELECT * FROM SaldoActual ORDER BY FechaRegistro DESC LIMIT 1")
    suspend fun getUltimoSaldo(): SaldoActual?

    @Insert
    suspend fun insertSaldo(saldo: SaldoActual)

    @Update
    suspend fun updateSaldo(saldo: SaldoActual)

    @Delete
    suspend fun deleteSaldo(saldo: SaldoActual)

    @Query("SELECT * FROM SaldoActual WHERE SaldoId = :id")
    suspend fun getSaldoById(id: Int): SaldoActual?
}